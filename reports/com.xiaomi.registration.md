# 运营商服务 16.0.5

对象：`运营商服务_16.0.5.apk`，实际包名 `com.xiaomi.registration`，versionCode 69，minSdk 33 / targetSdk 34。SHA-256：`3dac308f0088d3bbfb342a5b9e9096fab0ddf56f737603df282fcf0b698708dd`。

## 主要结果

确认存在三条不同的注册通道：HTTPS 设备注册、条件性的 CTEI 明文 HTTP 注册、二进制短信注册。短信不经过 IPv4/IPv6 OUTPUT 防火墙或 hosts。

## HTTPS 设备注册

入口包括 `AutoRegReceiver`、内部 `AutoRegIntentService` 和网络 job。`i1.b.u()` 检查 SIM、漫游、此前注册状态、默认数据卡、5G/软件变化等，再调度注册。

`RegistrationJobService.c` → `i1.g` → `z.f` → `i1.i` 加载 `res/xml/post_params*.xml` → 内部 `DeviceInfoProvider` / `j1.b` / `i1.c` 取值 → `i1.i.c` 组装 JSON → `i1.j.run` 实际发 POST。

实际请求字段包含：MEID、型号、软件版本、双 SIM CDMA/LTE IMSI、ICCID、双 IMEI、MAC、OS 版本、注册时间、默认数据卡、RAM/ROM、CELLID、TAC、5G/网络/接入类型、RCS/卫星开关、卡类型和电量。卫星变体另有第三组 IMEI/ICCID/IMSI；平板字段较少。缺卡、权限和厂商 API 状态可能导致空值。

`i1.j` 将 JSON 进行 `Base64.encode(...,0)`，用 `ByteArrayEntity` 发往：

- 手机：`https://zzhc.vnet.cn`
- 平板：`https://zzhc.vnet.cn/pi`

虽然 Content-Type 叫 `application/encrypted-json`，此链实际正文只是 Base64，不能称应用层加密；HTTPS 仍提供传输层保护。

证据：`jadx/sources/i1/i.java:157`；`i1/j.java:100`；`i1/c.java:415`、`:421`；`j1/b.java:434`、`:449`；`jadx/resources/res/xml/post_params.xml`；`证据/关键链路DEX核验.txt` 的 `Li1/j; run`。

## CTEI 明文 HTTP

`AutoRegIntentService` 的“无 radio 且有 ro.ril.oem.ctei”分支，或 `j1.c.e()` 特定设备条件，启动 `i1.a` / `i1.j` 的 CTEI 工作。后者涉及设备代号 ruan/koto/organ 且非 CT 定制，并检查网络连接。

实际字段是 `VER/CTEI/MAC/IP/UPLINKMAC/LINK/FWVER/DATE`：CTEI 来自系统属性，特定设备可回退 IMEI0；UPLINKMAC 为 Wi-Fi BSSID，IP 为当前 IPv4。Gson 转 JSON、URLEncoder 编码后直接拼入：

`http://typdm.tydevice.com?jsonstr=...`

这里既没有 HTTPS，也没有应用层加密；URL 编码不提供保密性。完整 URL 还会写入 Log.i。这是本批样本中能明确追到字段和发送 API 的敏感信息明文 HTTP 路径之一。

失败后有计数和最多前三次的 30 分钟延时重试，不是无间隔无限发送。

证据：`jadx/sources/com/xiaomi/registration/autoregistration/AutoRegIntentService.java:63`；`j1/c.java:126`；`i1/a.java:22`、`:27`；`i1/j.java:259`、`:264`、`:279`、`:323`；`k1/a.java`。

## 短信注册

普通 SIM 分支 `g.p.s(slot)` 读取型号、双 IMEI、当前 IMSI、软件版本和运营商/主副卡标志，拼成 `<a><b>...<c>...` 文本，加 `{4,3,length,0}` 头后交给 `i1.m`。

短信线程实际调用：`SmsManager.sendDataMessage("10659401", null, (short)0, message.getBytes(), sentIntent, deliveryIntent)`。这是数据生成到发送 API 的完整链，不是只找到了短信权限或一个号码。未见该消息使用 AES/RSA；二进制头不等于加密。

卫星路径 `i1.b.i()` 另外构造 `<a1><b1>...`，包含卫星 IMEI 属性、另一 SIM 的 IMEI、当前 slot 的 IMSI、型号与软件版本，加 `{9,3,length,0}` 头，仍交给相同发送器。`SmsFilterReceiver` 处理回复和注册结果。

证据：`jadx/sources/g/p.java:317`、`:370`；`i1/m.java:31`、`:44`、`:65`；`i1/b.java:170`、`:340`；DEX `Li1/k; run` 中 sendDataMessage。

## 触发门槛与同意

已按原始 DEX 核验 `AutoRegReceiver.onReceive`：检查设备 radio/CTEI、`ro.product.autosms`、CU/CM 定制、注册型号、用户 0，以及 `device_provisioned==1` 或向导完成事件。网络注册还有 SIM、漫游、注册状态和延迟门槛。

普通周期分支存在 15 天阈值，其他事件与重试另有调度，不能把所有请求都说成每 15 天一次。在所分析的业务链中未发现读取 `operator_service_improve_plan` 的同意开关。

证据：`AutoRegReceiver` DEX 0x0046–0x017c；`i1/b.java:367`；`AutoRegIntentService.java:105`。

## 组件与覆盖范围

`AutoRegReceiver` 导出且没有 receiver 级 permission，但系统广播、protected-broadcast 和 ROM 外部保护不能只凭 exported 属性判断可被任意 APP 利用。`RegistrationJobService` 要求 BIND_JOB_SERVICE；`AutoRegIntentService` 明确不导出；现代 target 下的 `DeviceInfoProvider` 没有公开 exported=true。

已检查单 DEX、manifest、注册参数 XML，导出 1057 个 Java 文件；本 APK 没有 .so。Jadx 两处报错及部分坏分支已针对入口、发送端、字段获取做 DEX 核验。原始 DEX、调用证据与错误索引均保留。
