# SystemHelper 4.0.0-20260910

对象：`SystemHelper_4.0.0-20260910.apk`，实际包名 `com.mobiletools.systemhelper`。APK SHA-256：`42fbfebedd8711b2ca22d3d969324f7ed7c757d838e143bf6cf4d2e8e1ed8556`。versionCode 34，minSdk 31 / targetSdk 34。

## 主要结果

确认存在设备/SIM标识、应用使用情况、按UID统计的流量、蓝牙信息、位置和无线信号的采集、组包与上传链。

这个版本默认先走 `https://dm.wo.com.cn:18081/register`，SSLException 后可退回 `http://dm.wo.com.cn:18080/register`。正文经过应用层 AES-GCM。
## 设备、SIM 与网络数据

`OnBootReceiver` 调度注册和后续任务；中央分发 `f1.p.d` 根据命令构造注册/应用/信号线程。Jadx 将部分分支错误还原为“只创建不启动”，原始 DEX 的 R/B/F/S/T 分支均能到达 `Thread.start`，已经交叉核验。

- `f1.a`、小米适配 `f1.n`：通过 MIUI/Telephony API 获取双 IMEI，按订阅获取 ICCID、IMSI 和无线小区数据。
- `e1.b.b()` 组装实际 JSON：设备与软件版本、IMEI/IMSI/ICCID、小区标识/MNC/TAC/CID/PCI、5G/VoNR 状态、Wi-Fi BSSID/信号/频率/链路速率、IPv6、eSIM 等。字段是否有值取决于设备、SIM 与实际授予权限。
- `f1.a.h()` 使用 `getBondedDevices()`，收集已配对蓝牙名称与 MAC。

证据：`jadx/sources/e1/b.java:165`；`f1/a.java:45`、`:437`；`f1/n.java`；`证据/关键链路DEX核验.txt` 的 `Lf1/p;`、`Lf1/a;`。

## 应用使用记录与流量

`e1.f.e()` 读取 `getInstalledApplications` 及 `queryAndAggregateUsageStats`，再计算应用使用记录；主列表来自使用统计，安装列表用于名称和 UID 等信息。默认统计窗口是约 24 小时。

`g1.b.a()` 把以下字段写入上报 JSON：`appLabel`、`pkgName`、`runTime`、`launchCount`，SIM1/SIM2 的收发及总流量（取整 KB），以及 Wi-Fi 总量（取整 MB）。`launchCount` 的实现还统计 UsageEvents 的类型 1 和 7，因此不是严格、去重后的用户启动次数。流量查询使用 `NetworkStatsManager.querySummary`，然后按应用 UID 汇总。

证据：`jadx/sources/e1/f.java:195`、`:205`、`:226`；`g1/b.java:45`；`f1/p.java:35`。

## 定位与信号上传

`GetSignalInfoReceiver` 配置百度定位高精度模式与 bd09ll 坐标，结果进入信号记录；`g1.n.a()` 将经纬度、海拔/地址信息、定位精度、SIM/小区标识以及 RSRP/RSRQ/SINR 等无线信号字段写入 JSON。记录先进入本地数据库，`f1.p.d` 的 R 注册流程读取并上传，失败有待重传记录。

`e1.g.c()` 还有国家/运营商、Wi-Fi 或联通网络等门槛；广播到达不代表每次必定成功上传。百度 SDK 中另有 `loc.map.baidu.com`、`ofloc.map.baidu.com`、`api.map.baidu.com`、`daup.map.baidu.com` 等定位/配置链路。

证据：`jadx/sources/com/mobiletools/systemhelper/control/GetSignalInfoReceiver.java`；`g1/n.java:107`；`e1/g.java:80`；`f1/p.java`。

## 用户同意门槛

`g1.l.a()` 与 `f1.a` 的厂商分支存在差异：vivo 分支同时检查 `device_provisioned` 和 `operator_service_improve_plan`；小米分支 `f1.n.v()` 只检查 `device_provisioned`。

定位接收器也沿用这一厂商分支，代码主动向百度 SDK 设置隐私已同意标记。它证明程序设置了该标记，不能单凭静态 APK 还原用户此前看过什么系统界面、是否在其他地方同意过。

证据：`jadx/sources/g1/l.java:60`；`f1/a.java:272`、`:350`；`f1/n.java:48`；`GetSignalInfoReceiver` 定位初始化处。

## 传输与密码实现

`e1.g.a()`：JSON → SHA-256 派生 32 字节 AES key → 用 APK 资源内公钥进行 RSA/ECB/PKCS1Padding 包装 → AES/GCM 加密正文。GCM 标签 128 位；IV 使用 key 的前 8 与后 8 字节。key/IV 由载荷确定性派生，而不是独立安全随机生成。

默认服务器写在 `g1.d`：HTTPS 18081 与 HTTP 18080。`g1.f.c.checkServerTrusted()` 仅检查证书非空和有效期，没有进行可信 CA 链验证；HTTPS 连接仍保留默认 HostnameVerifier。

**降级已用原始 DEX 的异常表核实**：HTTPS 主体字节 `[0x016a,0x028a)` 将 `SSLException` 导向 `0x02e6`，该分支令标志 `d=true`，重抛地址 `0x0340` 只受 catchall 清理覆盖，不会经过普通 Exception 分支将标志清零。外层 `e1.g.a()` 读取该标志后改走 HTTP 18080。代码日志称其为证书错误，但实际捕获范围是 SSLException，包括其他 TLS 握手/协议异常。降级正文仍为加密载荷，HTTP 缺少传输层身份、元数据和响应保护。

证据：`jadx/sources/e1/g.java:41`、`:55`；`g1/c.java:17`、`:62`；`g1/f.java:45`、`:85`、`:181`；`g1/j.java`；`证据/关键链路DEX核验.txt` 的 `Lg1/f; g` 方法 TRY 表。

## 导出服务、日志与身份

这份 4.0 样本的 `RegisterHelperService`、`TestActivity` 和开机接收器要求 `com.mobiletools.systemhelper.permission`，保护级别为 signature。服务有修改 `serverAddress` 的方法。

完整 JSON 日志受 `preference_enable_log` 控制，默认 false。
清单声明共享组 `com.mobiletools.systemhelper.uid`，不是 `android.uid.system`。实际 UID 及是否与其他包共享，需按手机包管理器映射。它的证书 SHA-256 为 `874b8ca2297f53a373b1b935c134cb0eb1ff7381e54491b88c643a6b9c46db77`，主体含 CU/RI/NewDM。

## 覆盖范围

已检查 manifest、资源、单 DEX、550 个 Java 导出文件；两份原生库 `libindoor.so` / `liblocSDK8b.so` 保留了字符串、符号与 JNI 入口证据。

Jadx 报 6 处错误；关键线程分发、TLS、密码和组包链已补 DEX 核验。相关材料见[样本信息](../evidence/com.mobiletools.systemhelper/样本信息.json)、[解码清单](../evidence/com.mobiletools.systemhelper/AndroidManifest.xml)、[反编译错误索引](../evidence/com.mobiletools.systemhelper/反编译错误索引.json)和[原生库检查](../evidence/com.mobiletools.systemhelper/原生库检查.json)。本报告是静态实现证据。