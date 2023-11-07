# DingVideo使用说明
首先将项目从github的main分支上拉取下来：
```shell
git clone git@github.com:Tamiflu233/RandomLearning.git
```
接着通过编译器打开项目，本项目前端采用VSCode编译器进行开发，后端基于IntelliJ IDEA环境进行开发。
用VSCode打开dingvideofrontend文件，这里默认nodejs、vue3和npnm已经安装完成。在TERMINAL黑框中输入以下代码：
# 安装项目前端所需要的工具包
```shell
pnpm install
```

# 运行前端代码
```shell
pnpm run dev 

```

如果终端没有报错，在浏览器中输入 http://localhost:9527/ 地址即可访问项目的前端。

打开IntelliJ IDEA，导入项目后端文件dingvideobackend，这里默认IDEA中java1.8和maven已经配置完毕。如果pom.xml文件中有报错，说明后端环境所需要的部分依赖未导入，等待maven自动把依赖导入即可。

点击进入src/test/java/com/dataispower/dingvideobackend/DingvideobackendApplicationTests.java文件，选中如下运行按钮：

![图片](http://s32s1qs47.bkt.clouddn.com/img/project_snapshot.PNG)

控制台没有报错或停止，则项目后端程序成功运行，这时候进入 http://localhost:9527/ 地址即可以体验本项目。
