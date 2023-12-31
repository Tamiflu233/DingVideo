# DingVideo使用说明

## 1. 安装项目

首先将项目从github的main分支上拉取下来：

```shell
git clone git@github.com:Tamiflu233/DingVideo.git
```

接着通过编译器打开项目，本项目前端采用VSCode编译器进行开发，后端基于IntelliJ IDEA环境进行开发。

> 🚩请务必先运行项目后端再运行前端

## 2. 后端运行

打开IntelliJ IDEA，导入项目后端文件 ``dingvideobackend``，这里默认IDEA中java1.8和maven已经配置完毕。如果pom.xml文件中有报错，说明后端环境所需要的部分依赖未导入，等待maven自动把依赖导入即可。

点击进入 ``src/main/java/com/dataispower/dingvideobackend/DingvideobackendApplication.java``文件，选中如下运行按钮：

![图片](http://s32s1qs47.bkt.clouddn.com/img/project_snapshot.PNG)

控制台没有报错或停止，则项目后端程序成功运行。

## 3. 前端运行

用VSCode打开 ``dingvideofrontend``文件，这里默认nodejs、vue3和pnpm已经安装完成。在TERMINAL黑框中输入以下代码：

```shell
# 安装项目前端所需要的工具包
pnpm install
# 运行前端代码
pnpm run dev
```

如果终端没有报错，在浏览器中输入 http://localhost:9527/ 地址即可访问项目的前端。

## 4. 演示视频

视频地址：http://s32rmapm1.bkt.clouddn.com/DingVide%E6%BC%94%E7%A4%BA%E8%A7%86%E9%A2%91.mp4
