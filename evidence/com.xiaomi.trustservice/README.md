# com.xiaomi.trustservice 证据索引

样本：`MiTrustService_1.2.260901.apk`；版本：`1.2.260901`。

[对应分析报告](../../reports/com.xiaomi.trustservice.md)

## 样本与清单

- [样本信息.json](%E6%A0%B7%E6%9C%AC%E4%BF%A1%E6%81%AF.json)
- [AndroidManifest.xml](AndroidManifest.xml)
- [requested_permissions.txt](requested_permissions.txt)
- [组件清单.csv](%E7%BB%84%E4%BB%B6%E6%B8%85%E5%8D%95.csv)
- [dex-statistics.json](dex-statistics.json)
- [反编译错误索引.json](%E5%8F%8D%E7%BC%96%E8%AF%91%E9%94%99%E8%AF%AF%E7%B4%A2%E5%BC%95.json)
- [原生库检查.json](%E5%8E%9F%E7%94%9F%E5%BA%93%E6%A3%80%E6%9F%A5.json)
- [清单阅读说明.md](%E6%B8%85%E5%8D%95%E9%98%85%E8%AF%BB%E8%AF%B4%E6%98%8E.md)

## 原始指令核验

- [关键链路DEX核验.txt](%E8%AF%81%E6%8D%AE/%E5%85%B3%E9%94%AE%E9%93%BE%E8%B7%AFDEX%E6%A0%B8%E9%AA%8C.txt)

## 源码与资源

路径沿用样本解包目录；文件内容保持原样，供核对报告中的类名、方法与行号。

- [jadx/sources/A0/o.java](jadx/sources/A0/o.java)
- [jadx/sources/G/a.java](jadx/sources/G/a.java)
- [jadx/sources/G/b.java](jadx/sources/G/b.java)
- [jadx/sources/M/b.java](jadx/sources/M/b.java)
- [jadx/sources/X0/b.java](jadx/sources/X0/b.java)
- [jadx/sources/androidx/emoji2/text/o.java](jadx/sources/androidx/emoji2/text/o.java)
- [jadx/sources/com/ifaa/seccam/IFAASecCamService.java](jadx/sources/com/ifaa/seccam/IFAASecCamService.java)
- [jadx/sources/com/ifaa/seccam/c.java](jadx/sources/com/ifaa/seccam/c.java)
- [jadx/sources/com/ifaa/seccam/f.java](jadx/sources/com/ifaa/seccam/f.java)
- [jadx/sources/com/xiaomi/trustservice/IMiTrustService.java](jadx/sources/com/xiaomi/trustservice/IMiTrustService.java)
- [jadx/sources/com/xiaomi/trustservice/common/CommService.java](jadx/sources/com/xiaomi/trustservice/common/CommService.java)
- [jadx/sources/com/xiaomi/trustservice/face/FaceManagerProxyImpl.java](jadx/sources/com/xiaomi/trustservice/face/FaceManagerProxyImpl.java)
- [jadx/sources/com/xiaomi/trustservice/i.java](jadx/sources/com/xiaomi/trustservice/i.java)
- [jadx/sources/com/xiaomi/trustservice/j.java](jadx/sources/com/xiaomi/trustservice/j.java)
- [jadx/sources/com/xiaomi/trustservice/ota/SocidProvision.java](jadx/sources/com/xiaomi/trustservice/ota/SocidProvision.java)
- [jadx/sources/com/xiaomi/trustservice/remoteauthservice/k.java](jadx/sources/com/xiaomi/trustservice/remoteauthservice/k.java)
- [jadx/sources/com/xiaomi/trustservice/remoteauthservice/l.java](jadx/sources/com/xiaomi/trustservice/remoteauthservice/l.java)
- [jadx/sources/com/xiaomi/trustservice/remoteauthservice/m.java](jadx/sources/com/xiaomi/trustservice/remoteauthservice/m.java)
- [jadx/sources/com/xiaomi/trustservice/remoteauthservice/remoteAuthService.java](jadx/sources/com/xiaomi/trustservice/remoteauthservice/remoteAuthService.java)
- [jadx/sources/com/xiaomi/trustservice/rootpub/PubCertRevise.java](jadx/sources/com/xiaomi/trustservice/rootpub/PubCertRevise.java)
- [jadx/sources/com/xiaomi/trustservice/secmedia/c.java](jadx/sources/com/xiaomi/trustservice/secmedia/c.java)
- [jadx/sources/com/xiaomi/trustservice/secmedia/d.java](jadx/sources/com/xiaomi/trustservice/secmedia/d.java)
- [jadx/sources/com/xiaomi/trustservice/signature/MiTrueDeviceSignatureService.java](jadx/sources/com/xiaomi/trustservice/signature/MiTrueDeviceSignatureService.java)
- [jadx/sources/com/xiaomi/trustservice/unifiedencryptservice/c.java](jadx/sources/com/xiaomi/trustservice/unifiedencryptservice/c.java)

## 原生库文本记录

下列文件是导出符号或静态字符串；工具输出头部的本机路径已转为样本相对路径。与报告中的调用链和反汇编记录配合阅读。

- [libmicontinuity_sdk.so.strings.txt](native-evidence/libmicontinuity_sdk.so.strings.txt)
- [libmicontinuity_sdk.so.symbols.txt](native-evidence/libmicontinuity_sdk.so.symbols.txt)
- [libmievent.so.strings.txt](native-evidence/libmievent.so.strings.txt)
- [libmievent.so.symbols.txt](native-evidence/libmievent.so.symbols.txt)
