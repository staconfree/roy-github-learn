1、cat-client 3.0.0 的jar包如果没有，需要自己编译
2、客户端启动前，需保证/data/appdatas/cat 下有 client.xml 文件
```
<?xml version="1.0" encoding="utf-8"?>
<config xmlns:xsi="http://www.w3.org/2001/XMLSchema" xsi:noNamespaceSchemaLocation="config.xsd">
    <servers>
        <server ip="127.0.0.1" port="2280" http-port="8080" />
    </servers>
</config>
```
3、resources/META-INF/app.properties 下的 app.name 的名字要跟 CAT上项目名称一致
4、出现 Error when try connecting to /127.0.0.1:2280 类似的问题，参考https://www.cnblogs.com/softidea/p/5719331.html
 修改 全局系统配置-》客户端路由 下面的 default-server 的id 改成服务端的对外ip


