# nacos
> nacos服务配置启动

# 目录
* [1 启动nacos](#01)

## <div id="01"></div>
## 1 启动nacos
    1.1、docker pull nacos/nacos-server
    1.2、docker run -d -p 8848:8848 -e MODE=standalone nacos/nacos-server
    1.3、http://localhost:8848/nacos/index.html
    1.4、账号密码：nacos nacos