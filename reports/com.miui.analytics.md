# Analytics 6.33.0

输入 `Analytics_6.33.0.apk`；包名 `com.miui.analytics`；versionCode 2026091500。versionName 的资源引用已由 `jadx/resources/res/values/strings.xml:3` 解出 **6.33.0**，不只依赖文件名。SHA-256：`a3656199f013e2f68bc20022f941122a13ce99e33c5df1cef054c9267189d73f`。未声明共享 UID；本包没有 .so。

下文 `e/...`、`c/...` 均分别指 `jadx/sources/a/b/a/e/...`、`jadx/sources/a/b/a/c/...`。

## 主要结果

这是具有完整统计实现的服务，包含应用信息/使用事件采集、本地数据库、配置与重试、加密上传、自更新，以及向 XMSF 交数据的分支。

## 采集内容与本地存储

| 内容       | 已核对代码                                                                                                                                                |
| -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------- |
| 已安装应用信息  | `e/h0/m.java:107` 实调 getInstalledPackages，构造 pkg/vn/vc/installer/fit/lut/status/ot_enable/apps 等。存在筛选和坏反编译分支，不能断言所有场景无筛选。                            |
| 使用事件     | `e/h0/y.java:1120` 实调 UsageStatsManager.queryEvents；`:195` 构造 pkg/class/ts/duration/vn，`:616` 处理前后台时序。不是只有权限声明。                                      |
| 公共设备/广告头 | `e/f.java:58`–`:171` 包含 OAID、条件性 GAID/instance_id、机型、系统、版本、网络、地区等。                                                                                   |
| 活跃事件/标识  | `e/h0/b.java:451` 构造 Android ID/VAID/UDID/MAC/IMEI/MEID/IMSI 等相关字段；`:188` 的 TZ 构造还有双 IMEI/SN/i1/i2/非国际版账号名 mid。标识读取见 `e/l0/c.java:294`，有门控/异常/空值处理。  |
| 事件队列     | `e/f0/f.java:214` 建 onetrack 的 events 表，含 appid/package/event_name/priority/data/timestamp/type；`:1003` 实际 insert。旧链另有 analyticsv2.db（`c/q/b.java`）。 |
| 上报/重试    | `e/f0/z.java:49` 处理事件、压缩加密，`:116` 构 track/v4；`e/i0/c.java:281` 是发送通道。`e/f0/y.java:49`、`:110` 安排失败重试。                                                 |

`AnalyticsApp.java:43` 初始化 Alarm / 更新 / JobScheduler，`:79` 注册包增删更新事件。`e/l0/r.java:388` 的体验计划、`a/b/a/b/a.java:72` 的使用报告/地区条件与按事件云控仍存在。

## 有条件的 DoH 与 IP 缓存重试

已追到真实可达链：`e/i0/c.java:281` 先走普通上传，网络/解析类异常且匹配 tip 策略时，`:291` 重试 `g(...,true)`；该分支同时要求 f=true，而 f 初始化为 `r.q()`，对应 IS_INTERNATIONAL_BUILD（`e/l0/r.java:318`）。

`e/i0/j/b.java:601` 改用自定义 DNS 客户端，`:739` 装 resolver，`:63` 合并 DoH 与持久化 IP 缓存；`:751` 构 `https://dns.google/dns-query`，bootstrap 为 8.8.4.4 / 8.8.8.8。策略 `net_to_okhttp_or_dns_data` 的 tok/tip/ut/ct 由 `e/i0/c.java:419` 读取。

因此是**国际版、策略与失败条件下的上传可达通道**，不是一段无调用的库代码。

## 跨进程交给 XMSF

`com/miui/analytics/internal/collection/AppenderManager.java:194` 把输入材料 JSON 加入 aaid 和 iced，显式广播到：

`com.xiaomi.xmsf/com.xiaomi.xmsf.push.service.receivers.XMSFUploadReceiver`

action 为 `com.xiaomi.xmsf.push.XMSF_UPLOAD_ACTIVE`，extras 含 pkgname、category=analytics_app_material、name=pulse、data=JSON；发送时指定接收权限 `com.xiaomi.xmsf.permission.USE_XMSF_UPLOAD`。iced 来源 `c/v/n.java:177` 的 IMEI MD5；材料字段包括 i/it/iv/in/ik。

上层检查 XMSF 版本>=108，还检查调用方白名单/签名/系统应用等条件（AppenderManager `:88`、`:119`、`:171`）。Jadx 有不自然的布尔恢复，已保留原始 DEX。

这是已确认的**本地跨包交接实现**。XMSF APK 不在7个输入里，其最终请求、域名与实际是否接收不能由本包补写。仅封 analytics 自身 UID 不能保证阻止已交给另一进程的数据。

## 自更新与校验

`c/j.java:874` 下载更新 APK、可选 MD5、固定证书摘要验证后才 rename 到 shared/modules/m.apk；`:932` 在 force==1 时交安装器。`c/v/c.java:55` 对首张签名证书的 MD5 与固定值比较；`c/c.java:129` 使用 PackageInstaller Session 或旧版 installPackage，`:121` commit。

ForApi 另一条 `c/k.java:78` 查询 sdkconfig(.intl).xiaomi.com，`:159` 下载，`:185` 同样签名验证。可以更新未来执行版本，但不是无签名限制的任意下载执行。未证实本样本把任意远程 DEX 无校验加载；modules / analytics.apk 路径本身不够作为该结论。

## 端点与漏项

| 主机/路径 | 用途/源点 |
|---|---|
| tracking.miui.com/track/v4 | OneTrack；`e/y.java:117`、`e/f0/z.java:116` |
| tracking.miui.com/track/v1 | 旧统计，upload_url 可由策略指定；`c/u/g.java:33`、`c/t/e.java:88` |
| **om.tracking.miui.com/track/mt** | 国内 EventMonitor 上传；`jadx/sources/a/b/a/a/a/c/a.java:1590` |
| tracking.india/rus/eu/intl.miui.com | 区域路由；`e/y.java:33`、`:117`；IN 有 pn-tracking-bak.onetrack.mi.com 备份 |
| sdkconfig.xiaomi.com 及 india/rus/eu/intl 变体 | 配置、更新；`e/y.java:92`；/api/v4/detail/config、config_common、/api/ex/lastUseful、/api/checkupdate/lastusefulversion2；IN 有 pn-sdkconfig-bak.onetrack.mi.com |
| tracking 路由主机 /track/key_get、/track/getNonce | 密钥和 nonce；`e/g0/e.java:104`、`e/f0/o.java:177` |
| tracking 路由主机 /track/upload | 日志文件；`jadx/sources/a/b/a/g/b.java:209` |
| dns.google/dns-query | 有条件 DoH，不是事件接收地址 |
| pubsub.googleapis.com/v1/projects/.../topics/...:publish | 海外 PubSub，需要 project/topic/token 配置；`jadx/sources/a/b/a/g/e/c.java:165` |
| http://api.ad.xiaomi.com/video/uploadLog?appKey=PHONE_VIDEO | 旧 ReporterService 返回地址；`c/s/b.java:58`。存在方法不等于当前已触发，实际HTTP是否被策略限制未实测 |

区域与备份映射还可从配置更新（`e/e0/d.java:236`、`e/y.java:154`）。
## 覆盖与边界

单 DEX、507个 Java 导出文件、manifest/资源均已检查；Jadx8处报错与原始 DEX 保留，关键采集/跨包/网络类另有 `证据/关键链路DEX核验.txt`。未发现本 APK 自带不可见 DEX 壳或原生 .so。

