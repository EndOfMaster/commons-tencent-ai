# Tencent AI Open Platform(腾讯AI开放平台)
![image](https://img.shields.io/badge/maven-1.0.1-green.svg)

## 步骤
1. 注入TxAiClient的bean或创建对象
2. 创建对应的request对象
3. 调用txAiClient的execute
4. 调用response的successful方法判断是否请求成功
5. 获取想要的参数

## 已有接口
- 智能闲聊

## Maven Repository
    https://maven.pkg.github.com/EndOfMaster/maven-repo
### Maven Import
```xml
 <dependency>
   <groupId>com.endofmaster</groupId>
   <artifactId>commons-tencent-ai</artifactId>
   <version>x.y.z</version>
   <type>pom</type>
 </dependency>
```
### Gradle Import
```groovy
 compile 'com.endofmaster:commons-tencent-ai:x.y.z'
```