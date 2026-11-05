# HyperOS4-XSOF Analysis

HyperOS 系统组件与 XSOF 相关功能的 APK 静态逆向分析记录。分析日期：2026-11-05。

本次整理了 7 个样本，围绕屏幕云检、72 小时行为记录、反诈风险评分、设备注册和统计分析的数据流展开。每份报告按实际包名、版本与 SHA256 对应到具体样本。

## 从这里开始

- [总报告](reports/总报告.md)：主要发现、调用链以及域名用途汇总。
- [端点与通道表](已核验端点与通道.csv)：61 条网络、跨进程和短信通道记录，附触发条件、代码位置及核验状态。
- [样本清单](样本清单.csv)：7 个 APK 的文件名、版本、包名和 SHA256。
- [动态加载与本地命令](reports/动态加载与本地命令.md)：自更新、引擎下载、签名校验与本地命令的用途。

## 七个分析对象

| 名称                   | 包名                             | 样本版本              | 分析报告                                          | 证据                                                    |
| -------------------- | ------------------------------ | ----------------- | --------------------------------------------- | ----------------------------------------------------- |
| 系统安全组件／GuardProvider | `com.miui.guardprovider`       | 3.1.4-20260902.0  | [阅读](reports/com.miui.guardprovider.md)       | [查看](evidence/com.miui.guardprovider/README.md)       |
| 安全服务／安全中心            | `com.miui.securitycenter`      | 13.6.5-261003.0.1 | [阅读](reports/com.miui.securitycenter.md)      | [查看](evidence/com.miui.securitycenter/README.md)      |
| MiTrustService       | `com.xiaomi.trustservice`      | 1.2.260901        | [阅读](reports/com.xiaomi.trustservice.md)      | [查看](evidence/com.xiaomi.trustservice/README.md)      |
| SystemHelper         | `com.mobiletools.systemhelper` | 4.0.0-20260910    | [阅读](reports/com.mobiletools.systemhelper.md) | [查看](evidence/com.mobiletools.systemhelper/README.md) |
| 统计分析                 | `com.miui.analytics`           | 6.33.0            | [阅读](reports/com.miui.analytics.md)           | [查看](evidence/com.miui.analytics/README.md)           |
| 运营商服务                | `com.xiaomi.registration`      | 16.0.5            | [阅读](reports/com.xiaomi.registration.md)      | [查看](evidence/com.xiaomi.registration/README.md)      |
| 手机管家入口               | `com.miui.securitymanager`     | 21.0              | [阅读](reports/com.miui.securitymanager.md)     | [查看](evidence/com.miui.securitymanager/README.md)     |

## 主要发现与详细说明

### 1. 确实有录制 3 秒视频并上传检测的功能，但有触发条件

GuardProvider 会录制一段 3 秒、分辨率为 720×1280 的 MP4 视频，采用 H.264 编码。随后读取整段视频，用 Base64 编码放入 JSON，再通过 PCC 应用层加密，上传到 `detection-aigc.engine.miui.com/aiVideo/pcc/detection` 进行检测。这里上传的是整段视频，不只是检测结果。

按照安全中心正常入口的逻辑，需要设备支持、AI 检测开关已开启，并满足协议与必要权限要求；还会检查微信／QQ 视频相关场景和摄像头状态。样本中的 AI 开关默认关闭。检测服务实现了标题为“通话防护中”的前台通知。

视频录制、上传检测和用户主动提交“误报反馈”是不同的流程。语音检测另有一条链路，详见逐包报告。

[GuardProvider 报告](reports/com.miui.guardprovider.md) · [安全中心上游条件](reports/com.miui.securitycenter.md) · [录屏链原始 DEX 指令](evidence/com.miui.guardprovider/证据/录屏链DEX核验.txt)

### 2. 确实有最近 72 小时的行为记录和分析，查询的是特定事件及汇总结果

安全中心会在手机本地记录截图时间、屏幕共享、普通通话和网络通话、飞行模式、呼叫转移、安装应用的类别等事件，再查询最近 72 小时内的时间段、持续时长及相关汇总。这些记录与目标应用自己的文件分开保存，卸载相关应用后，事件记录仍可能保留。

**截图表只记录“什么时候截图”，屏幕共享表记录状态与持续时间。这两项记录不包含截图或视频画面。** 前面提到的 3 秒视频属于另一条 AI 检测流程。

#### 谁能调用这套接口

XSOF 会核对真实调用来源、包名与凭据，检查调用频率和后端授权。这里的“获授权调用方”是通过这套接入检查的调用方。

通过检查后，安全中心将汇总 JSON 交给 MiTrust；MiTrust 再核对调用来源，交给厂商的底层服务处理。这 7 个 APK 不包含后续 HAL／TEE 的实现，因此这里只能追踪到交接位置。

外部研究中出现过小红书、抖音、闲鱼、支付宝等 App：

- [一份真机分析](https://tech.chrisli.me/posts/hyperos-antifraud/)列出了持有 `ACCESS_XSOF` 权限的多个 App，并观察到小红书绑定相关服务；持有权限或绑定服务。
#### 反诈风险分是什么

反诈模块会把部分事件、持续时长和时间窗权重组合，计算风险指标。这个 APK 自带的[默认配置](evidence/com.miui.securitycenter/raw/assets/score_config.json)中，有这些基础分值：

| 事件 | 基础分值 |
| --- | ---: |
| 屏幕共享 | 10 |
| 呼叫转移 | 6 |
| 风险应用 | 15 |

这些数值是配置中的计算参数，不是这台手机当前得分；计算还涉及时间窗和权重，云端配置也可能覆盖默认值。

可追到的链路是：安全中心计算结果 → MiTrust／厂商底层服务处理 → 返回获授权的调用方。至于分数高了会怎样，[小米的业务文档](https://dev.mi.com/xiaomihyperos/documentation/detail?pId=2243)列出的使用场景包括风险提示、增加安全验证、拦截高风险交易等。
[安全中心完整报告](reports/com.miui.securitycenter.md) · [MiTrustService 报告](reports/com.xiaomi.trustservice.md) · [XSOF 指令核验](evidence/com.miui.securitycenter/证据/关键链路DEX核验.txt)

### 3. SystemHelper 4.0 有完整的信息收集、保存和注册流程

`com.mobiletools.systemhelper` 会采集设备和 SIM 卡标识，包括两组 IMEI，以及 IMSI、ICCID；还可追到应用使用情况和流量、蓝牙配对设备、位置与无线信号信息的采集代码。这些数据经过数据库保存、整理组包，再发送到联通的 `dm.wo.com.cn` 进行设备注册。

同一份 APK 内，小米适配分支检查首次开机设置流程是否完成；vivo 适配分支还会检查 `operator_service_improve_plan` 设置项。两者不是同一种门槛。这里确认的是样本内存在 vivo 适配代码，没有提取另一台 vivo 设备的样本进行验证。

**本次 4.0 样本默认使用 HTTPS 的 18081 端口，正文另外采用 AES-GCM 加密。** 但其自定义服务器证书验证缺少可信 CA 链检查；特定 `SSLException` 可触发退回 HTTP 的 18080 端口，降级路径已用原始 DEX 异常表核实。

退回 HTTP 时，正文仍有应用层加密，不能把这个版本说成所有字段都以裸 JSON 明文发送。与此同时，证书验证缺陷、协议降级，以及密钥／IV 的确定性派生问题需单独看待。

[SystemHelper 报告](reports/com.mobiletools.systemhelper.md) · [TLS 与降级指令核验](evidence/com.mobiletools.systemhelper/证据/关键链路DEX核验.txt)

### 4. 另一个“运营商服务”包有未加密的 HTTP 上传，以及数据短信注册

`com.xiaomi.registration` 在 CTEI 条件分支中，会把 CTEI（某些情况下回退到 IMEI）、MAC 地址、Wi-Fi 热点标识 BSSID、本机 IPv4、版本、时间等数据组成 JSON，经 URL 编码后放进网址参数，向 `http://typdm.tydevice.com?jsonstr=...` 发出 GET 请求。**这条分支没有 HTTPS，也没有额外加密正文；URL 编码不是加密。**

普通的设备注册流程则使用 HTTPS，载荷另做 Base64 编码。这里真正保护传输的是 HTTPS，Base64 本身也不是加密。

此外，样本中还有把 IMEI、IMSI、设备型号等组成二进制数据短信，发送到 **10659401** 的实际 API 调用链。

这个包声明与电话服务共用 `android.uid.phone`。如果按整个共享 UID 断网，可能连带影响电话相关服务。

[运营商服务报告](reports/com.xiaomi.registration.md) · [CTEI 与短信指令核验](evidence/com.xiaomi.registration/证据/关键链路DEX核验.txt)

### 5. 统计分析不只有最初名单里的几个域名

国内事件监控还存在 `om.tracking.miui.com/track/mt` 通道。hosts 按完整域名精确匹配时，写入 `tracking.miui.com` 不会顺带屏蔽 `om.tracking.miui.com`，也不会覆盖其他地区或备用域名。

国际版代码在特定配置和网络请求出错条件下，存在通过 Google 加密 DNS（DoH）查询 IP、或者使用缓存 IP 重试的可达路径。

此外，另有分支把材料附上 AAID 标识、经过 MD5 处理的 IMEI，再交给另一个系统组件 XMSF 的接收器。XMSF 不在这 7 个样本中，后续处理尚未闭环。

[统计分析报告](reports/com.miui.analytics.md) · [统计分析证据索引](evidence/com.miui.analytics/README.md) · [端点与通道表](已核验端点与通道.csv)

### 其他相关功能

样本中还存在 NFC 读卡访问相关配置、应用名单和语音云检等功能，详见报告与证据。

安全中心的地震预警、志愿者、报警快速定位和 ADB 安装验证还与部分业务共用域名。[总报告](reports/总报告.md)与[安全中心报告](reports/com.miui.securitycenter.md)中分别列出了对应路径。

## 文件说明

```text
README.md                    阅读入口
reports/                     总报告、7 份逐包报告及补充分析
apks/                        本地原始 APK（*.apk 已加入忽略规则）
evidence/                    按包整理的关键证据与源码
样本清单.json / .csv          样本身份、版本、哈希
已核验端点与通道.json / .csv  通道、载荷、条件和代码位置
输入与DEX一致性核验.json      原始分析时的输入与导出一致性记录
反编译质量与覆盖.json         导出规模、错误数量及时间记录
```

`evidence/` 内保留解码 Manifest、权限与组件清单、关键原始 DEX 指令文本、原生库符号／字符串记录以及 Java 和资源文件。报告中的 `jadx/sources/`、`raw/` 等路径以原样本解包目录为基准，可在相应包的证据目录中查找已收录部分。各包的证据索引列出具体文件。

原始 APK 保存在本地 `apks/`，已有 `*.apk` 忽略规则。原始 DEX、原生库二进制及完整反编译目录另行留在本地；`evidence/` 收录报告对应的文本证据。原始程序指令与反编译 Java 分开保留，便于核对控制流和反编译错误。

## 分析范围

这些结论来自指定版本 APK 的静态实现，关键链路另有原始 DEX 指令核验。

本次样本分析没有执行这些 APK、请求其业务服务器或发送短信。实际设备是否触发某次行为，需要结合运行记录；其他机型、地区或新版 APK 可按新的样本哈希另行补充。
