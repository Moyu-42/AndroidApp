# 编程新技术实务 实验4

结合前面实验2和实验3完成的一个实现了注册登录功能的安卓app。

具体过程可以详见 设计文档.pdf

### 环境

```shell
Ubuntu 18.04.4 LTS

mysql  Ver 14.14 Distrib 5.7.31, for Linux (x86_64) using  EditLine wrapper

openjdk 11.0.8 2020-07-14
OpenJDK Runtime Environment (build 11.0.8+10-post-Ubuntu-0ubuntu118.04.1)
OpenJDK 64-Bit Server VM (build 11.0.8+10-post-Ubuntu-0ubuntu118.04.1, mixed mode, sharing)

Apache Tomcat/9.0.39

安卓8.1 SdkVersion 27
```

### 项目结构

```
├─Exp4_Android
│  ├─.gradle
│  │
│  ├─.idea
│  │  
│  ├─.settings
│  ├─app
│  │  ├─.settings
│  │  ├─build
│  │  ├─libs
│  │  ├─release                                         // 打包生成的apk文件
│  │  │   ├─Moyu's World.apk
│  │  │   └─output-metadata.json
│  │  └─src
│  │      ├─androidTest
│  │      ├─main
│  │      │  ├─AndroidManifest.xml
│  │      │  ├─java
│  │      │  │  └─com
│  │      │  │      └─moyu
│  │      │  │          └─exp4_android                  // android的activity
│  │      │  │             ├─ChangePassword.java        // 用户页修改密码
│  │      │  │             ├─ForgetPassword.java        // 登录页忘记密码
│  │      │  │             ├─Loginfo.java               // 登陆成功后用户页信息
│  │      │  │             ├─MainActivity.java          // 登录页（主界面）
│  │      │  │             ├─Modify.java                // 用户页修改信息
│  │      │  │             └─Register.java              // 注册
│  │      │  └─res
│  │      │      ├─drawable
│  │      │      ├─drawable-v24
│  │      │      ├─layout
│  │      │      │      ├─activity_login.xml            // 主界面
│  │      │      │  	├─changepassword.xml            // 修改密码
│  │      │      │  	├─forgetpassword.xml            // 忘记密码
│  │      │      │      ├─loginfo.xml                   // 登录信息
│  │      │      │      ├─modify.xml                    // 修改信息
│  │      │      │  	└─register.xml                  // 注册
│  │      │      ├─mipmap-anydpi-v26
│  │      │      ├─mipmap-hdpi
│  │      │      ├─mipmap-mdpi
│  │      │      ├─mipmap-xhdpi
│  │      │      ├─mipmap-xxhdpi
│  │      │      ├─mipmap-xxxhdpi
│  │      │      ├─values
│  │      │      └─values-night
│  │      └─test
│  │
│  │
│  ├─build
│  └─gradle
│
└─Exp4_Server
    ├─.idea
    │  └─artifacts
    ├─out
    ├─src
    │  ├─bean
    │  │  	├─Database.java
    │  │  	├─MD5.java                              // 密码放入数据库时进行加密
    │  │  	├─Person.java
    │  │  	└─User.java
    │  ├─dao
    │  │ 	├─PersonOpt.java
    │  │  	└─UserOpt.java
    │  ├─service
    │  │ 	├─PersonService.java
    │  │  	└─UserService.java
    │  └─servlet
    │     	├─ChangePasswdServlet.java
    │     	├─DeleteServlet.java
    │     	├─DownloadServlet.java
    │     	├─GetInfoServlet.java                   // web端获得登录信息
    │     	├─GetLogInfoServlet.java                // 安卓端获得登录信息
    │     	├─LoginServlet.java
    │     	├─ModifyInfoServlet.java
    │     	└─RegisterServlet.java
    └─web
    	├─change_password.jsp
        ├─forget.jsp
        ├─index.jsp
        ├─login_info.jsp
        ├─modify.jsp
        ├─register.jsp
        ├─download
        │  	└─Moyu's World.apk
        ├─js
        │  	├─login&forget.js
        │  	├─login_info.js
        │  	├─modify.js
        │  	├─register.js
        │  	└─validator.js
        └─WEB-INF
            ├─classes
            └─libs
```

