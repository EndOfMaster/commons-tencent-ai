# Tencent AI Open Platform(腾讯AI开放平台)

## 步骤
1. 注入TxAiClient的bean或创建对象
2. 创建对应的request对象
3. 调用txAiClient的execute
4. 调用response的successful方法判断是否请求成功
5. 获取想要的参数

## 已有接口
- 智能闲聊

## Maven Repository
  https://dl.bintray.com/endofmaster/maven

version: ![image](https://img.shields.io/badge/maven-1.0.0-green.svg)

### Maven Import

the x.y.z is version

```xml
 <dependency>
   <groupId>com.endofmaster</groupId>
   <artifactId>commons-TxAi</artifactId>
   <version>x.y.z</version>
   <type>pom</type>
 </dependency>
```

### Gradle Import

```gradle
 compile 'com.endofmaster:commons-TxAi:x.y.z'
```