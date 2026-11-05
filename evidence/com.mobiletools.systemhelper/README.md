# com.mobiletools.systemhelper 证据索引

样本：`SystemHelper_4.0.0-20260910.apk`；版本：`4.0.0-20260910`。

[对应分析报告](../../reports/com.mobiletools.systemhelper.md)

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

- [jadx/sources/com/mobiletools/systemhelper/Activity/TestActivity.java](jadx/sources/com/mobiletools/systemhelper/Activity/TestActivity.java)
- [jadx/sources/com/mobiletools/systemhelper/control/GetSignalInfoReceiver.java](jadx/sources/com/mobiletools/systemhelper/control/GetSignalInfoReceiver.java)
- [jadx/sources/com/mobiletools/systemhelper/control/OnBootReceiver.java](jadx/sources/com/mobiletools/systemhelper/control/OnBootReceiver.java)
- [jadx/sources/com/mobiletools/systemhelper/service/RegisterHelperService.java](jadx/sources/com/mobiletools/systemhelper/service/RegisterHelperService.java)
- [jadx/sources/e1/b.java](jadx/sources/e1/b.java)
- [jadx/sources/e1/f.java](jadx/sources/e1/f.java)
- [jadx/sources/e1/g.java](jadx/sources/e1/g.java)
- [jadx/sources/f1/a.java](jadx/sources/f1/a.java)
- [jadx/sources/f1/n.java](jadx/sources/f1/n.java)
- [jadx/sources/f1/p.java](jadx/sources/f1/p.java)
- [jadx/sources/g1/b.java](jadx/sources/g1/b.java)
- [jadx/sources/g1/c.java](jadx/sources/g1/c.java)
- [jadx/sources/g1/d.java](jadx/sources/g1/d.java)
- [jadx/sources/g1/f.java](jadx/sources/g1/f.java)
- [jadx/sources/g1/j.java](jadx/sources/g1/j.java)
- [jadx/sources/g1/l.java](jadx/sources/g1/l.java)
- [jadx/sources/g1/n.java](jadx/sources/g1/n.java)

## 原生库文本记录

下列文件是导出符号或静态字符串；工具输出头部的本机路径已转为样本相对路径。与报告中的调用链和反汇编记录配合阅读。

- [libindoor.so.strings.txt](native-evidence/libindoor.so.strings.txt)
- [libindoor.so.symbols.txt](native-evidence/libindoor.so.symbols.txt)
- [liblocSDK8b.so.strings.txt](native-evidence/liblocSDK8b.so.strings.txt)
- [liblocSDK8b.so.symbols.txt](native-evidence/liblocSDK8b.so.symbols.txt)
