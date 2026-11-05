# 手机管家 21.0：桌面入口与快捷方式壳

输入：`手机管家_21.0.apk`；实际包名 `com.miui.securitymanager`，版本21.0 / code21，minSdk28 / targetSdk36。SHA-256：`a8a84fe0cb2e6d733120a972f1a9d073798fbb70746b3824fafe47ec50253019`。
## 实际调用

- `com/miui/securitymain/SCMainEntryActivity.java:19` 投递 `e1.a`；`e1/a.java:47` 构建 action `miui.intent.action.SECURITY_CENTER`、package `com.miui.securitycenter`，随后 startActivity 并 finish。这是 Activity/Intent 跳转。
- `c1/a.java:32` 管理动态快捷方式，`:92` 构建安全扫描、杀毒快捷入口。`g1/b.java:59` 的杀毒与流量目标分别是安全中心的 MainActivity、NetworkAssistantActivity；红包设置也跳转该包。它没有在本包实现这些后台服务。
- `ShortcutReceiver.java:17` 处理 shortcutType / pkg 并管理快捷方式；`ShortcutProvider.java:18` 查询桌面 favorites，`:66` 按 URI 返回入口存在状态。管理 Activity、Receiver、Provider 要求外部权限 `com.miui.security.manager.SHORTCUT`；该权限等级不能只从本包推定。
- `s2/a.java:16` 反射访问 MimotionModeService 的 Binder，这是 MiMotion 配置依赖，不能说成安全中心 Binder 代理。

以上 Java 路径均相对 `jadx/sources/`；组件原始权限见 `AndroidManifest.xml` 与 `组件清单.csv`。

## 网络、加载与原生库

业务代码未检出实际 HTTP(S) 请求、Socket 连接创建或向安全中心 bindService 的调用；也没有发现此包独立的上报域名。

`i2/b.java:112`、`:116` 确有 PathClassLoader，依次尝试加载 `/system_ext/framework/MiuiBooster.jar` 或 `/system/framework/MiuiBooster.jar`。这是本地系统性能库加载。

内含一份 `libsec-common-utils.so`（331496字节），SHA-256 为 `97aca8d0da3003fe767764250753defeff66eef76417c88bd14192e9044e1efc`。导出 ConfigManager 的 getHttpKey、getUuidKey、getEncodeSecretKey 等 JNI；本包未找到对应 ConfigManager/loadLibrary 业务调用。符号名含 Http 不足以证明它在本包上传数据；其动态导入表未见 socket/connect/getaddrinfo/send/recv/SSL/curl。

原生证据保留在 `native-evidence/libsec-common-utils.so.{strings,symbols}.txt`。

## 对组件判断的意义

本包导出1428个 Java 文件，Jadx报4处错误；快捷方式 Provider 有错误还原的局部变量。原始DEX、manifest、清单、错误索引和原生证据全部保留。
