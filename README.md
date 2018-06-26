# Tencent AI Open Platform

## 腾讯AI开放平台

## 步骤
1. 注入QqBasicApi的bean或创建对象
2. getBaseAuthorizeUrl或getMobileAuthorizeUrl创建跳转微博页面
3. getOauth2AccessToken获取accessToken
4. getOauth2OpenId获取openId
5. getOauth2UserInfo查询用户信息

## Maven Repository
  https://dl.bintray.com/endofmaster/maven

version: ![image](https://img.shields.io/badge/maven-1.0.0-green.svg)

### Maven Import

the x.y.z is version

```xml
 <dependency>
   <groupId>com.endofmaster</groupId>
   <artifactId>commons-qq</artifactId>
   <version>x.y.z</version>
   <type>pom</type>
 </dependency>
```

### Gradle Import

```gradle
 compile 'com.endofmaster:commons-qq:x.y.z'
```