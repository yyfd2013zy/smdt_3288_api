
# 此项目可以生成自定义签名的APP和共享System进程的系统签名的APP，
- 生成两种不同的APP需要修改签名和AndroidManifest.xml文件，
- 请参考AndroidManifest.xml。


# 自定义文件签名: /keystore/zwj_sign.jks
- password:android
- alias:key0


# 系统签名文件：/keystore/keystore.jks
- password: android
- alias: keystore

# build version

- compileSdkVersion 28
- buildToolsVersion "28.0.3"
- gradle-6.7.1-bin.zip
- com.android.tools.build:gradle:4.2.1

