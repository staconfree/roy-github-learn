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

5、配置服务端的时候，需要保证/data/appdatas/cat 下有 datasources.xml 文件
```
<?xml version="1.0" encoding="utf-8"?>

<data-sources>
        <data-source id="cat">
                <maximum-pool-size>3</maximum-pool-size>
                <connection-timeout>1s</connection-timeout>
                <idle-timeout>10m</idle-timeout>
                <statement-cache-size>1000</statement-cache-size>
                <properties>
                        <driver>com.mysql.jdbc.Driver</driver>
                        <url><![CDATA[jdbc:mysql://127.0.0.1:3306/cat]]></url>
                        <user>root</user>
                        <password>root</password>
                        <connectionProperties><![CDATA[useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&socketTimeout=120000]]></connectionProperties>
                </properties>
        </data-source>
</data-sources>
```

6、配置告警参考：https://github.com/dianping/cat/blob/master/cat-doc/posts/ch2-config/alarm.md
首次更改配置后要重启才会生效

7、看大众点评如何通过实时监控系统CAT打造7*24服务 http://www.itshuji.com/technical-article/616.html


