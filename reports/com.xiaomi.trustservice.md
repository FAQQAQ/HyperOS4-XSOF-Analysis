# MiTrustService 1.2.260901

输入 `MiTrustService_1.2.260901.apk`；包名 `com.xiaomi.trustservice`，code12260901，minSdk31 / targetSdk36。SHA-256：`2344a2e8be65fffd3a0f2140527a075e40e865d55e5b615d2407af307305d37f`。声明共享组 android.uid.system，usesCleartextTraffic=false、allowBackup=false。下文源码路径相对 `jadx/sources/`。

## 主要结果

本包承接 XSOF 可信状态、设备证书/密钥、安全摄像头、生物识别和跨设备认证。XSOF 行为 JSON 确实进入这里，进一步交厂商 HAL；APK 内没有该 HAL 实现。

确认两组 HTTPS 请求：设备证书/密钥配置，以及 FID/SOCID 绑定。另有真实摄像头帧交付客户端，未找到该帧链继续接到 HTTP 的证据，也没有找到屏幕录制链。

具体实现问题有：反射权限检查异常时返回允许；安全摄像头 XMS 二次认证在部分错误时继续；一个 SystemUI 回调未做同类权限检查。

## 1. XSOF n8 → getTrustStatus → HAL

安全中心的 n8 对应本包 `IMiTrustService.getTrustStatus(String,byte[],String)`；依据相同 descriptor 和事务编号2。

`com/xiaomi/trustservice/j.java:23` 实际执行：Binder调用PID → 系统 security 服务查询真实包名 → 只允许 com.miui.securitycenter / com.xiaomi.mitrusttest30 → 解析 JSON → 等待 vendor.xiaomi.hardware.mrm.IMrm/default → `getStatusByAuth(str.getBytes(),requestType.getBytes(US_ASCII),bArr)` → 拆出首字节状态码与余下响应字符串返回。

证据：`IMiTrustService.java:30`、`:56`、`:72`；`j.java:25`、`:33`、`:45`；`i.java:18`；`A0/o.java:466`、`:1218`。Jadx遗漏了 FutureTask.get(2,SECONDS)，原始DEX已证实等待存在。

## 2. 两组实际云请求

### 设备证书/密钥

`rootpub/PubCertRevise` 是导出服务，未设服务专属 manifest permission；onStartCommand 启动后台任务，G.a 按 action 区分：

| action | HTTPS 请求 | 参数与结果 |
|---|---|---|
| rootpub.PubCertRevise | find.api.micloud.xiaomi.net/mic/find/v4/anonymous/device/externalkey | keyType=cert、fid、clientData；返回 encryptedPrivateKey 交 certificateLoad |
| rootpub.RotateKeyRevise | 同上 | keyType=misec、fid、clientData；返回材料及 sign 交 rotateKeyLoad |

数据来自 IMiCertificate/default 的 certificatePrepare 等 HAL 方法，不能把不透明 clientData 强行解释为应用列表/IMEI/行为历史。网络超时5秒，实际读响应再解析。

证据：`rootpub/PubCertRevise.java:27`、`:31`、`:37`、`:54`、`:82`、`:119`、`:179`；`G/a.java:47`。初始化 c() 的 Java 条件反转，原始DEX证明 HAL 非null返回true。

### FID / SOCID 绑定

`ota/SocidProvision.java:24` 读取 ro.boot.socid，空则退出；经 W0.a 取FID；计算SHA-256并调用后端签名，结果长度为128字符才实际POST：

`https://deviceapi.micloud.xiaomi.net/mic/binding/v1/anonymous/device/security/socid`

字段 cloudsp_fid、cloudsp_socid、cloudp_sign；FID与SOCID在URL参数，签名不等于参数加密，传输保护来自HTTPS。成功写本地状态，入口在`:102` / `M/b.java:113`。

## 3. 安全摄像头实际帧数据

IFAA分支：`com/ifaa/seccam/IFAASecCamService.java:28` 与 `c.java` 接口检查 CAMERA，使用SharedMemory/NativeHandle连接 seccam HAL 或兼容 miface HAL（`f.java:136`）。

MiSecCam分支：`secmedia/d.java:48` 的 openMiSecCam 首先调用 MiSecCamService.a() 检查调用方CAMERA，之后连接 IMiSecMediaService/default、做XMS认证、分配512000字节共享内存，调用misec_media_init/get_data。onBind只返回Binder，不是在绑定和open时检查两次。

`secmedia/c.java:29` 真正映射共享内存、取长度、复制帧字节并回调客户端。这是实际图像链，但没有找到其继续HTTP上传的路径；也不是屏幕MediaProjection录制。

`G/b.java:107` 会生成 OpenSecCam 事件：appId31000401973、调用包SHA-256前16字节十六进制、CallTimes=1，经MiSight.nativeSendEvent交libmievent。确认的是本地原生事件设施交接，最终网络去向不在这条Java链中。
## 4. 已核验的检查问题

### 反射异常放行

`androidx/emoji2/text/o.java:107` 的G(uid,permission)反射ActivityManager.checkUidPermission，catch后返回0，而调用方将0解释为允许；J(uid)反射UserHandle.getAppId，catch同样返回0。CAMERA、REMOTE_AUTH、TRUST_SERVICE_INTERNAL的运行时检查使用这些helper。

DEX：G 的catch 0x0050→0x0058 const0→return；J 的catch 0x004c→0x0054 const0→return。正常时把完整UID转换appId后检查，多用户语义也需设备验证。可确定异常时fail-open，不能确定实际ROM必然触发异常或足以越过HAL。

### XMS 认证部分错误继续取图

`secmedia/d.java:108` 仅错误-200/-300拒绝返回132；-100异常/连接错误、-110超时等记录后继续HAL。DEX 0x013c取结果，0x016e–0x017a只排这两个错误，其他到0x0182；0x0248/0x0274调用摄像头HAL。

这说明二次认证异常未统一拒绝，但CAMERA检查与HAL仍在，不能说任意应用无CAMERA即可拍照。

### SystemUI 回调检查差异

`remoteauthservice/m.java:14` 的 systemUIcallRemoteAuth 未调用其他业务方法使用的remoteAuthService.a权限检查，只判callback非空后进入状态对象E。相比 `remoteauthservice/l.java` 是缺少一层同类检查；配对/锁屏/硬件认证等后续条件仍在，未证明可据此解锁。

## 5. 其他功能边界

| 功能           | 已检查边界                                                                                                                                          |
| ------------ | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| 跨设备认证        | remoteauthservice/remoteAuthService.java:37检查REMOTE_AUTH；k.java:505涉及账号、锁屏凭据、sys.locksettings.remoteauth.enable、强认证/配对                         |
| 设备状态         | X0/b.java:26检查TRUST_SERVICE_INTERNAL                                                                                                           |
| Continuity通道 | common/CommService.java:139建立mitrustservice通道到NChannel/JNI；Manifest的入口有BIND_CONTINUITY_LISTENER_SERVICE                                        |
| 设备签名         | signature/MiTrueDeviceSignatureService.java:56进入mitruedevice HAL；Manifest signature权限及b.java:185证书检查；输出nonce/fid/包名/extra/signature/cert_chain |
| 人脸代理         | face/FaceManagerProxyImpl.java:38检查PID进程白名单及证书，再转系统FaceManager，不能只看exported就称无鉴权                                                               |
| 统一数据密钥       | unifiedencryptservice/c.java查询真实调用包，允许短信/文件管理/相册/笔记/指定压力测试包，使用AndroidKeyStore与AES-256-GCM；未见本地密钥操作HTTP上传                                       |
| SEProtector  | Manifest signature权限、指定action、本地密钥与厂商安全服务                                                                                                      |

嵌入证书中 ca.mioffice.cn 等网址不是实际网络调用证据。

## 6. 原生与覆盖范围

2个随包ELF：libmicontinuity_sdk.so承接跨设备通道（SHA256 bed18f540f843802619fd41b6dbe946ac5c3200c1a213b362bb9dd41d21e78c1）；libmievent.so承接MiSight（d404076edc917984b6af87c5013c0e428f5acd9cf3fa18211e1124937ee24d3f）。已保留字符串/符号，未发现可直接核定的新业务云域名；不是全部native语义证明。

未找到下载任意DEX/ELF后执行的业务链；加载随包库不能当恶意更新。单DEX有1802类/10523方法，导出1600个Java，Jadx日志计4项错误；错误索引4条，对应2处库方法的失败。关键可信接口、初始化、权限与相机分支补原始DEX，保存在 `证据/关键链路DEX核验.txt`。
