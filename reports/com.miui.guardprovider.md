# 系统安全组件 3.1.4-20260902.0

输入 `系统安全组件_3.1.4-20260902.0.apk`；实际包名 `com.miui.guardprovider`，versionCode330，minSdk33 / targetSdk37。SHA-256：`6cdb14123ca32f36883e74d968f390e30d1c64589f500fd48f9f9b9757253928`。清单无 sharedUserId。

下文源码路径均相对 `jadx/sources/`；混淆类如 x7.java 位于 `defpackage/`。

## 主要结果

**确实存在“录3秒屏幕片段，把整段 MP4 加密提交云端检测”的完整实现。** 它不只在用户标记误报时才上传视频。录制、云端检测、阳性提示和误报反馈是不同步骤。

该样本的正常上游有 AI 反诈功能开关、协议/权限与微信/QQ场景检查，服务受signature|privileged 权限保护，还有前台通知。

另外确认非系统应用列表的上行与风险结果的本地跨包查询。杀毒引擎/病毒库更新、OneTrack 与推送 SDK 也属于本包网络面。

## 1. 3秒录屏的启动与捕获

`com/miui/guardprovider/aiguard/AiGuardService.onStartCommand()` 读取 COMMAND/CAPABILITIES：1是语音、2是视频、3是 daily_report 统计。COMMAND2 → `r7.java:54` → 服务 `c():175` → `x7`；不是 COMMAND3 传入的开关统计字段在这里决定是否录制。

`u23.java:25` 通过隐藏 `IMediaProjectionManager.createProjection(当前UID,包名,0,false[,0])` 创建 MediaProjection，没有走普通应用的 createScreenCaptureIntent 结果 token 流程。它依赖系统投屏特权。

`re3.java:755` 设置 MediaRecorder：SURFACE、MP4、H264、720×1280、24fps、1048576bit/s；VirtualDisplay 使用当前屏幕尺寸/density，因此是屏幕捕获，不只是直接取得远端摄像头网络流。这个视频函数不设置音轨。

`x7` 的常量是录制3秒、阴性后等待2秒；实际流程是 start → delay3秒 → stop → 云检测 → 阴性后延迟2秒再下一段，还包含网络耗时，不能说严格每5秒一段。命中后弹 AlertActivity 并停止；取消/异常释放。临时文件前缀 clip_ 位于 cache。

清理：`re3.y():777` reset、projection.stop、释放 VirtualDisplay；`w7.java:94` 命中则另存反馈副本，临时片段最终删除。未证明所有异常路径的反馈副本都有固定清理时限。

注意 `AiCore.java:859` 的3000是绑定AI服务重试延迟上限，**不是3秒录屏证据**；真正时长来自 x7。x7 的 Java 还原失败，以保留指令与 `证据/录屏链DEX核验.txt` 为准。

## 2. 真正的视频上行载荷

`AiGuardService.e():289` 打开 PCC 会话，读取整段 MP4：Files.readAllBytes → Base64 → JSON：

`{streamName:"clip.mp4", fileBase64:整段视频, requestId:随机UUID}`

然后 `kk3.o():378` 使用 `l00.b` 进行应用层加密，再调用 `k43`。`y6.java:26` / `x8.java:71` 设置基础地址：

- 会话证明：`https://detection-aigc.engine.miui.com/pcc/attestation/token`
- 视频检测：`POST https://detection-aigc.engine.miui.com/aiVideo/pcc/detection`

接口表单字段 data 为 EncryptedData；响应同样解密再读 checkRes。此路径没有找到降级为明文视频的 fallback。

`l00.java:76` → `wp1.o():161` → `af3.e():120` 使用 AES/GCM/NoPadding、随机12字节IV、128bit tag；ECDH 共享秘密与 version/session公钥/key_id/digest/data_bytes 外层结构均在代码中。客户端还存在证书/nonce验证代码。

检测/反馈共用 `t20(1)` → `pq(3)` 拦截器，`pq.java:524` 每次实际 HTTP 反射调用 `GuardApplication.isAllowConnectNetwork`，false 抛 Network access disabled，true 才 proceed。attestation 用独立 rg3 客户端，是否经过相同拦截器没有证实，不能混写。

## 3. 语音与误报反馈

常规语音检测：`si.java:60` 启动 AiCapability.startRecognize（zh-CN），`qq.java:12` 累积识别文字 → `a7` / `u7` → `AiGuardService.d():223` 组 requestId + text，加密 POST：

`https://detection-aigc.engine.miui.com/ai/audio/text/pcc/detection`

音频获取并非空实现：PHONE_SCAM_SCENE=2 使用 VOICE_CALL，场景3/4使用 VOICE_COMMUNICATION 加隐藏 AudioPolicy 的下行抓取；格式16kHz、PCM16、单声道。识别启动回调后才启动采集，经 AiCapability.postData 把 PCM 交给 **`com.xiaomi.aiasst.vision` 的 ACTION_TRANSLATE_SDK_SERVICE**。这是一处样本外 Binder 依赖；本链 mode=0，而SDK显式 offline helper 使用mode=1。

回传文字累积后进入上述加密云检；PCM也会写 feedback01.tmp，写入方式不保证反馈文件包含完整通话。证据：`es.java:17`、`:57`；`k63.java:31`；`uj.java:8`；`qi.java:46`；`pi.java:18`；`com/xiaomi/ai/vision/sdk/AiCore.java:51`、`:839`；`AiCapability.java:379`；`c7.java:27`。

误报反馈是独立的用户动作：AlertActivity 的“标记为误报” → d8/e8 → oj0。

- 视频反馈 JSON 含 fileType=MP4、requestId、fileBase64，仍是整段 MP4，PCC 加密发送 `/aiVideo/pcc/rbi`。
- 语音反馈 JSON 有 audioPCM 与 audioText，发送 `/ai/audio/text/pcc/rbi`。

证据：`AlertActivity.java:161`、`:211`；`d8.java:51`；`e8.java:28`；`oj0.java:7`。反馈文件位于 filesDir 的 feedback01.tmp / clip01.tmp（`n53.java:95`）。资源写“视频截图”，实现反馈载荷是 MP4。

## 4. 授权、上游与前台状态

`PermissionRequestActivity.java:168` 首次引导或 Secure AI_GUARD_PRIVACY_AGREEMENT 为0时显示隐私声明；资源明确提到收集通话语音、视频帧/片段用于识别，实际上行由第2/3节代码证明。`yz1.java:60` 同意后写协议标志；自有投屏提示的同意写 ai_guard_projection_permission，debug 属性或已存同意可跳过该自有提示。

服务和授权 Activity 要求 `com.miui.guardprovider.permission.Guard_Provider`，清单 protectionLevel=0x12（signature|privileged）。普通第三方应用不能只凭 exported=true 任意启动。服务本身未在每次 COMMAND2 重查协议，正常上游由受信任调用方实施门槛。

配套安全中心：CacheService 检查支持能力与 `isAiGuardEnabled`；后者要求默认false的功能开关、Secure协议标志和 Guard 麦克风权限。微信/QQ通话场景及摄像头使用状态匹配，才发 COMMAND2。设置页和 osbot 开启入口也有授权引导。详细源点见 `com.miui.securitycenter/分析报告.md` 的 AI Guard 补充节。

`AiGuardService` 会 startForeground，标题为“通话防护中”，所以不能说完全没有状态提示；投屏能力也不证明能绕过 FLAG_SECURE / DRM 受保护窗口。

停止条件除检测命中、通话/服务结束外，还含配置的服务生存时限及 thermal status>=3；默认时长的 Java 分支还原有问题，没有按伪 Java 写死一个分钟数（`n7.java:79`、`m7.java:19`、`z6.java`）。

`onDestroy():393` 将5个场景计数存入 ai_guard_daily_report；h7 的 daily_report 事件上报 enable_status，以及电话/QQ语音/QQ视频/微信语音/微信视频的 trigger_scenario + detect_number，完成后清计数。OneTrack appId=31000401489，联网开关由 GuardApplication 设置。证据：`h7.java:64`；`wu2.java:11`；`ro.java:214`。

## 5. 非系统应用列表上行

`ie2.d()` 遍历已安装包筛选非系统应用；`ie2.a()` 将每项的 pkg、version、签名 MD5、URL编码的 appname 放入 content，连同 timestamp、os、biz_id=virus_scan、uuid，交给 `ls1.d()` 实际 POST：

`https://flash.sec.miui.com/detect/app`

`ls1` 在此直接写 JSON，使用 HTTPS 与 sign；签名字段不是正文加密。uuid 来源 `n61.A():46`：读取持久化 key_uuid，没有则随机 UUID 保存。

触发包括 SecurityService Binder case18 在用户解锁后更新检测列表（`ni2.java:410`），以及病毒配置更新流程 `g.java:231`、`:384`。该函数检查 GuardApplication 允许联网：国内为本地 network_status==1 **或** persist.sys.sc_allow_conn；国际版还需安全中心 Privacy 状态，日本定制另需杀毒引擎联网开关。

另有新版 Flamingo `/risk/scan`：`rs1.java:25` 的 virus-engine.sec.miui.com base、`bx.java:14` 的列表请求、`kl0.java:358` 的实际组装，属于另一条云扫描架构。

## 6. 本地风险数据提供给 XSOF

`GuardProvider.call("getRiskAppData")`：读取 gray 风险缓存，按当前已安装的非系统包与相同版本匹配，排除白名单，将 packageName/versionCode/type/signaturesMd5 组成 riskData。缓存默认有效期3天，可配置。

原始 DEX 已纠正 Jadx 的明显倒置条件：相同包名才继续比较版本，不是 Java 字面上显示的“不相同才匹配”；对象存在才处理，不是 null 才 put。证据见 `证据/关键链路DEX核验.txt` 中 call 方法 0x042a–0x0524。

此 provider 要求 signature|privileged Guard_Provider 权限，不能当无权限公开应用列表接口。安全中心 eo.b 实际调用它并把 riskData 返回 XSOF 查询方，构成已确认的跨包数据交接。

## 7. 引擎、推送、统计与原生边界

本包还有 api.sec 的 antivirus/viruses、antivirus/whitelist、virus-engine 的云查、push-service.sec.miui.com，以及 OneTrack/推送/腾讯和 AVL SDK。

AVL 引擎与病毒库存在下载、MD5校验及 native installPackage（引擎安装，非 Android APK安装）链，详见根目录 `动态加载与本地命令.md`。已提取14个 .so 和 assets/mdetector 共15个 ELF，导出动态符号、带偏移字符串；mdetector 是 ELF32 ARM。

网络安全配置只显式允许 staging.api.sec.miui.com 与 tools.3g.qq.com 的明文域规则。

## 覆盖范围

单 DEX、6208个 Java 导出文件、manifest、资源与15个 ELF 已纳入检查；日志44项反编译错误、52个错误标记保留。对 x7、GuardProvider 等坏控制流，关键结论以原始 DEX 补证。