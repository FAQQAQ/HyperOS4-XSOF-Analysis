# 安全服务 / 安全中心 13.6.5-261003.0.1

对象：`安全服务_13.6.5-261003.0.1.apk`，实际包名 **`com.miui.securitycenter`**。

SHA-256：`ae9896193a2159cfce0ce1076ac41d370d0c4ed573c6ca002aaa50bfeda6b9b5`；versionCode 40001365，minSdk 26 / targetSdk 36，声明共享组 `android.uid.system`。请求 163 项权限，504 个组件。

## 核心结果

1. XSOF 具备近期行为数据库、72 小时窗口聚合、合作方鉴权、跨 MiTrustService 生成可信结果的完整实现。截图事件、屏幕共享状态、通话/VoIP、安装应用类别等都会进入这个体系。
2. **截图事件表只保存时间，不是截图图片。** 
3. 风险应用云查和防窥应用分类存在非系统应用列表上传实现。
4. 原先 12 域名之外还有 XSOF 合作方鉴权 `framework.sec.miui.com/framework/app/verification`。原 12 域名中，api.sec / srv.sec 的确承载地震相关和 ADB 验证功能，papa / eps 是紧急呼叫定位链。
5. 月报保留了本地事件与上传代码，但 **本构建的上传门槛恒为 false**。
## 1. XSOF 行为数据库

国内版 `Application.L()` 的远程进程初始化会启动 `MiFraudDetectionService`（`jadx/sources/com/miui/securitycenter/Application.java:335`、`:375`）。服务建立以下事件源与数据库：

| 类型 | 记录的内容 | 主要来源 |
|---|---|---|
| screenshot | `screenshort_time`，只有时间 | `miui.intent.TAKE_SCREENSHOT` 且 IsFinished |
| screen_share | 包名、开始/结束、是否活动、时长 | AppOps `MIUI:10042` 活动监听 |
| call_forwarding | 原因、是否开启、时间 | 电话状态/受保护 provider 输入 |
| airplane_mode | 开关与变化时间 | 飞行模式广播 |
| fraud_phone | 通话时间、号码 MD5、运营商/归属地、境内/虚商/风险状态、时长 | PHONE_STATE、电话辅助 API |
| voip_record | 包名、开始/结束、风险状态、时长 | AiGuardSceneService 场景 |
| fraud_url | 访问时间、类型，非完整 URL 字段 | 系统浏览器 provider 输入 |
| install_app | 包名、名称、来源、版本、MD5、安装时间、分类标签 | 市场/包安装器、病毒扫描结果 |

建表证据：`jadx/sources/jo/k.java:1470`。截图广播注册需要 `miui.permission.USE_INTERNAL_GENERAL_API`（服务 `:515`）；截图时间写入在 `:450` 左右。屏幕共享监听在 `:278` 与 `:556`。这里是“观察是否在共享屏幕”，没有创建录屏器或保存画面的调用。

`FraudDetectionProvider` 的安装数据输入只接受调用包 `com.xiaomi.market` 或 `com.miui.packageinstaller`，再检查平台签名；呼叫转移只接受 `com.android.phone`，风险 URL 只接受 `com.android.browser` 并校验平台签名。其公开 query 返回 null，delete/update 返回 0。

证据：`.../frauddetection/provider/FraudDetectionProvider.java:35`、`:64`、`:88`、`:124`。病毒安装广播另由 `a5/b.java:72` 写入数据库。

## AI Guard 补充：3秒视频的正常上游

本包 `CacheService.java:193`–`:203` 先检查 AiGuard 支持与启用状态才启动场景服务。`AiGuardUtils.java:61` 的启用条件是默认false的 SP 功能开关，加 `isPreRequirementsMet():88` 的 Secure协议标志与 Guard 的 RECORD_AUDIO 已授予。视频能力还检查机型/debug条件或 Guard 版本及投屏特权。

`AiGuardSceneService$d` 在微信/QQ通话场景中，根据能力位与当前摄像头活动，才启动 Guard 的 COMMAND2，附 VIDEO_SCAM_SCENE（`:416`–`:443`）。`ElectricProtectPhoneFragment.java:355` 的开启动作经过联网声明/PermissionRequestActivity，成功结果后设开关并启动；`FraudProtectHandler.java:94`–`:116` 的 osbot 开启也先查 CTA/前置协议权限。

Guard 后续确实捕获3秒屏幕 MP4并加密提交检测，详见其独立报告。这条链与只存截图时间的 XSOF screenshot 表不同。

证据：`jadx/sources/com/miui/electricrisk/{AiGuardUtils,AiGuardSceneService,ElectricProtectPhoneFragment}.java`；`com/miui/securitycenter/service/CacheService.java`；`证据/AiGuard上游与开关DEX核验.txt`。

## 2. 72 小时、卸载与返回数据

`jo.a` 的 SQL 使用当前时间减 `259200000` 毫秒过滤最近 72 小时；`lo.h.c` 将窗口分为不到1分钟、1–30分钟、30–60分钟、1–6小时、6–12小时、12–24小时、24–72小时。

`jo.k.q1()` 在写入相应事件时删除旧记录，不能推断设备即使长期没有新事件，也会恰好第72小时物理擦除所有行。安装记录与应用文件分开存储。

`ho.b` 构造 `CurrentFraudMetadata`、`PastFraudData`、`DurationFraudData` 和评分。过去行为接口主要返回按时间窗汇总的 `Map<Integer,Integer>` / 时长范围结果，不是向客户端直接开放完整原始逐条数据库。字段包括截图、屏幕共享、转移、飞行模式、SIM/VoIP 风险通话、风险 URL、金融/贷款/会议/风险/可疑/难辨应用类别。

证据：`jo/a.java:23`；`jo/k.java:1488`；`lo/h.java:1449`；`ho/b.java:47`、`:503`、`:1213`、`:1978`；`.../bean/{CurrentFraudMetadata,PastFraudData,DurationFraudData}.java`。

## 3. 合作方访问、鉴权和 MiTrust 交接

`MiSafetyDetectService` 根据 Binder 调用 PID 获取实际调用包；要求调用包声明 `com.xiaomi.security.permission.ACCESS_XSOF`，比对参数中的包名，并进行限频与后端鉴权。该权限为 normal 不能单独代表强信任边界，但服务还检查 appId / appKey / 签名 / 服务器授权。

`fo.a.c()` 实际向 `/framework/app/verification` 发送 appId、appKey、pkgName、timestamp、appSignature。基础地址在国内版 Application 初始化中设置为 `https://framework.sec.miui.com`。授权缓存有效期被限制在约 1–7 天。

`no.j` 显式 bind `com.xiaomi.trustservice.IMiTrustService`：把 `ho.b` 的行为 JSON、参数字节与请求类型交给 `n8(...)`，再把结果经原调用的回调返回。普通设备状态分支输入是调用包名称、版本与证书 SHA-256 JSON。该交接是本地 Binder，不是本包直接把整张行为表 POST 给 framework 域名；鉴权接口的载荷也不含那张表。

风险应用接口另会调用 `content://guard` 的 `getRiskAppData`，把 `riskData` 交给调用方（`eo/b.java:25`）。MiTrust 和 GuardProvider 的内部处理见各自报告。XSOF 自身还有 OneTrack 事件统计，记录调用包、鉴权类别、结果和错误码（`oo/d.java`、`com/miui/analytics/AnalyticsUtil.java:159`）。

证据：`.../xsof/MiSafetyDetectService.java` 的 `MiSafetyDetectImpl`；`fo/{a,b,d}.java`；`ed/a.java:8`；`Application.java:338`；`no/j.java:199`、`:230`、`:295`、`:372`；`证据/关键链路DEX核验.txt`。

## 4. 两条额外应用列表联网链

### 风险应用云扫描

`mi.b` 的请求任务先检查安全中心允许联网；支持条件还排除国际版/平板，并检查系统风险应用拦截 API 与网络。`qi.a.b(context, null)` 遍历已安装包，筛选非系统应用，生成包名、版本、应用名称、签名 MD5/SHA-256、APK 中 MANIFEST.MF 的 SHA-256（不是整个 APK 哈希）。传单个包名时只查该包。

`qi.c.f()` 将列表 Gson → gzip → 应用层加密，POST 到 `https://virus-engine.sec.miui.com/risk/scan`，另带 Android/ROM/设备、时间、扫描类型和 OAID（变量名 uuid，来源 `aj.y.a`）。`/risk/issueGrade` 用于取得风险说明/等级配置。

证据：`mi/b.java:255`、`:471`；`qi/a.java:24`、`:40`；`qi/c.java:37`、`:55`、`:280`、`:300`。

### 防窥应用分类

`z3.k` 优先读云配置/本地分类表，缺少配置时可枚举非系统应用，上传 apps 数组、系统/机型/时间/biz_id 到 `https://flash.sec.miui.com/user/cat`，取得应用分类；也有单应用查询。界面 `PeepProtectionSelectAppsActivity.b1()` 读取分类会触发缺失/过期刷新。

请求正文经过 `gf.a` 和 ConfigManager 的密钥处理；`z3.k.k()` 是直接 HttpURLConnection POST，没有像公共 `gk.l` 那样在该方法中再检查全局允许联网开关。

证据：`z3/k.java:57`、`:91`、`:121`、`:239`、`:260`、`:573`；`com/miui/antipeeping/v2/ui/PeepProtectionSelectAppsActivity.java:360`。这条有功能用途，同时确实上传应用包名列表。

## 5. 地震预警与志愿者：域名混用

`api.sec.miui.com/LBS/geoconv/admin` 实际发送 longitude / latitude 反查地区。后台自动更新要求地震预警开关（默认 false）；手动定位选城也能调用。
`RequestAreaCodeTask` 首先发送原始位置，随后另一个 NoisyLocation 任务再加 Laplace 噪声比较地区。

相关实际端点：

- srv.sec.miui.com：`/earthquake/warning/signature`、`supportCityCode`、`records`。
- api.sec.miui.com：`/universalWhitelist/checkUserId`；`/earthquake/volunteer/register`、`cancelRegister`、`countVolunteers`、`sensor/upload`。
- 传感器上传组包有 OAID、经纬度、时间、机型、ROM、可选 token 与传感器数组。志愿者有专用 monitor 开关（默认 false）、加入动作与中国网络等条件。

地震请求检查 `aj.z.D()` → `v1.b()`；国内版读取 `KEY_SECURITY_CENTER_ALLOW_CONNECT_NETWORK`，国际版还检查 MiuiSettings.Privacy。公共网络工具还可能添加设备/ROM/地区/语言/网络、Android ID、OAID、条件性的设备 ID 哈希。
证据：`com/miui/earthquakewarning/Constants.java:69`；`service/RequestAreaCodeTask.java:108`；`RequestAreaCodeTaskForNoisyLocation.java:43`；`UpdateAreaCodeManager.java:34`；`utils/Utils.java:197`；`EarthquakeWarningService.java:100`、`:255`；`EarthquakeMonitorDataManager.java:54`；`EarthquakeMonitorUtils.java:56`；`gk/l.java:129`、`:808`。

## 6. 报警快速定位：网络之外还有短信

国内紧急呼叫广播检查 110/120/119/122/12122，再检查中国 SIM / 网络与非漫游，启动未导出的 `PaService`。开关已启用时自动定位发送；关闭时显示可点击的定位提示，还有隐私引导。

`PaUtils.buildNetMessage` 包含 OAID 作 deviceId、本机手机号、紧急被叫、区号、呼叫时间、定位方式、地址、经纬度、定位开始结束与版本。实际 POST：`https://gateway-papa.cloud.weizhi.com/papa/upload`，包裹 channelNo 与加密 data；AES/CBC/PKCS5Padding、随机 IV，密钥由 APK 固定字符串派生。

网络空响应或取不到本机号码时，可向 `106903590009` 发短信；短信载荷经 RSA 公钥加密再编码。

EPS 另向 `https://eps.caict.ac.cn/eps/collect` 发数据，要求 EPS 开关开启，或已有 PA 开关且版本迁移条件成立；存储的 privacy_version 为空也能满足迁移条件，并自动打开 EPS 开关。另有国际版、12122 排除条件。字段包括主叫号码 SM3、被叫、机型名称、定位方式、位置/精度/海拔/地址与呼叫时间；sign 是固定 key 参与的 SM3，不是 RSA 签名。

PaService 可能为此临时开启移动数据，结束时按原状态恢复；通话中可每60秒重复、最多10次。以上属于紧急定位功能行为，并不把它写成 XSOF 接口。

证据：`kl/a.java:110`；`com/miui/warningcenter/policeassist/PaUtils.java:61`、`:84`、`:106`、`:379`、`:413`、`:450`；`PaLocationFloatingView.java:73`、`:120`；`EPSManager.java:56`、`:108`；`PaService.java:190`、`:293`、`:544`。

## 7. ADB 安装授权验证

`AdbInstallVerifyActivity` 在 ADB 授权设置场景，将小米账号 ID、时间和公共字段组成 JSON，RSA/ECB/PKCS1Padding 分块加密，POST 到 `https://srv.sec.miui.com/data/adb`。已有账号/is_input 分支发验证，否则引导登录；要求联网，特定机型区域有直接放行分支；Activity 不导出。

公共字段包括设备/运营商/地区/ROM/语言/网络、原 Android ID；名叫 imei 的字段实际上是 `getSmallDeviceId()` 的 MD5（空时回退 Android ID），不能仅按字段名断言原始 IMEI。SIM 字段在正常无线设备上来源 getSubscriberId（IMSI），无 radio 时另有回退。

证据：`com/miui/permcenter/install/AdbInstallVerifyActivity.java:45`、`:98`、`:158`、`:179`；`ff/f.java:62`；`ff/k.java:22`；`gk/d.java:151`；`com/miui/common/utils/j0.java:103`。

## 8. 月报：本地保留，上传硬关闭

`monthreport` 的 provider/数据库确实能记录 eventId、时间、包名、事件类型、版本与 data；组包代码还有设备/ROM/地区、Android ID 派生值与 OAID，保留 `/data/check` 和 `/data/upload` 两条请求实现。

但是 `com/miui/monthreport/c$d.b()` 必须满足 `z.D() && 网络可用 && td.c.a()`，**`td.c.a()` 只有 return false**。原始 DEX 已核验为 `const/4 v0,0; return v0`。写入 `key_month_report_enabled` 的 setter 也不能改变这个硬编码 getter。因此本样本的正常代码路径到不了实际月报上传，旧域名资料不能替代这份二进制的结果。本地事件记录则是另一个问题，不因网络 gate 关闭而消失。

证据：`com/miui/monthreport/b.java:126`、`:218`；`c.java:147`、`:151`、`:195`；`td/c.java:7`；`证据/关键链路DEX核验.txt` 中 `Ltd/c;`。

## 9. 本地敏感词与原生库

`SensitiveWordDetectProvider` 只允许调用包名 com.android.camera / com.miui.mediaeditor，将传入字符串交 native `hasSensitiveWords`，返回布尔值。

本包有20个 ELF，已导出符号/带偏移字符串。`libjni_load_serinum.so` 的 readOTP 进一步做了 ARM64 反汇编，确认打开 `/dev/otp`、ioctl 读取并返回字节；Java 调用在 `oj/h.java`。`libsec-common-utils.so` 提供网络/安全密钥 JNI；`liblib-riskcontrol.so` 提供敏感词检测；continuity 库含 socket/channel 通信实现。
## 分析范围

4个 DEX、32,975个类/209,243个方法的结构清单，13,282个 Java 导出文件，20个 ELF、manifest及资源均已导出和检索；人工沿以上关键链追踪，关键 XSOF/鉴权/服务与硬关闭门槛补原始 DEX。Jadx 69处报错及82个代码标记保留在错误索引中。

这是该版本静态实现的结果。屏幕短视频的实际执行链、MiTrust native 边界、Analytics 的代发链分别见另外三份报告。
