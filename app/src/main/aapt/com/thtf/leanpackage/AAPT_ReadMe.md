AAPT(Android Asset Packaging Tool)
|__AAPT的作用
|__AAPT基本命令
|__AAPT编译资源源码解析
|__AAPT打包和系统不一致的资源ID
----------------------------------------------------------------------------------------------------
一、Android系统的打包流程
   |__1.工程的资源文件(res文件夹下的文件),通过AAPT打包成R.java类(资源索引表)以及.arsc资源文件.
   |__2.若存在AIDL,会通过AIDL工具打包成java接口类.
   |__3.R.java和AIDL.java通过java编译成.class文件.
   |__4.源码class文件和第三方jar或library通过DX工具打包成dex文件;DX工具主要作用是将java字节码转换成Dalvik字节
   |____码,此过程会压缩常量池,消除冗余信息.
   |__5.apkbuilder工具会将所有没有编译的资源 .arsc资源|.dex文件 打包到一个apk中
   |__6.签名,APK通过配置的签名文件,jarsigner工具会对齐签名.得到一个签名后的APK->signed.apk
   |__7.zipAlign工具对signed.apk进行对齐处理.主要过程是将APK包中所有的资源文件距离文件起始偏移为4字节整数倍,这
   |____样通过内存映射访问APK文件时速度会更快.对齐作用主要是为了减少运行时内存的使用.
   
二、相关命令的作用
   |_aapt地址(cd your sdk path/build-tools/your build tools version/aapt)
   | |__aapt l Your APK Path  -> 列出压缩文件(zip | jar | apk)中的目录内容
   | |__aapt l -v Your APK Path -> 列出压缩文件中目录下更详细的内容(以列表的形式标识出很多参数)
   | |__aapt l -a Your APK Path -> 详细输出压缩文件中所有目录内容
   | |__aapt dump strings Your APK Path -> 打印apk中所有string资源表
   | |__aapt dump badging Your APK Path ->打印APK中的配置信息,包括包名,versionCode,versionName,platformBuildVersionName等.           
   | |  |__同时列出manifest.xml部分信息,包括启动界面,manifest里配置的label,icon等信息;还有分辨率,时区,users-feature等信息.     
   | |__aapt dump resource Your APK Path ->打印APK中所有资源信息,系统通过aapt构建出来的资源绝大部分资源ID都是以0x7f开头的.
   | |__aapt d xmltree Your APK Path  You APK XML File Path -> 直接反编译除了APK中某个XML布局文件的组织结构.
   | |__aapt d xmlstrings Your APK Path  You APK XML File Path -> 输出xml文件中所有的string信息.
   | |__aapt package -f -S Your apk Res Folder Path -I You SDK Path/platforms/Android Build Version
   | |  |__ -A Your APK assets Folder Path -M Your APK AndroidManifest.xml -F Your APK Path -> 将工程的资源编译到包里
   | |__aapt r Your ZIP PATH  Your APK Include Option File Path -> 删除一个zip中指定的文件
   | |__aapt a Your ZIP PATH  Your APK Include Option File Path -> 向zip中添加一个指定文件
   | |__aapt c -S Your APK Resources File Path -C Output Folder -> 对多个或单个资源文件夹处理,结果输出到文件夹中
   | |__aapt s -i Your APK Option File Path -o Output To File Path -> 预处理文件
   -------------------------------------------------------------------------------------------------
   