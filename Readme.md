# maintenance(设备维修)

> 模块解析

* maintenance-common: 公共模块
* maintenance-es: elasticserach 模块
* maintenance-mbg: 权限 + mybatis 模块
* maintenance-file: 文件上传模块
* maintenance-forest: 三方对接模块
* maintenance-sms: 短信发送模块
* maintenance-system: 系统代码模块
* maintenance-xxljob: 动态定时任务模块

执行 doc/sql 下的 maintenance.sql 文件，初始租户为测试，账号为 admin， 密码为 123456

doc/layout 为布局，不需要可以自行删除 maintenance-system/src/main/java/com/device/system/column 下的所有代码

> 需要注意，存入redis的实体类，其中LocalDate，LocalDateTime必须添加以下注解，否则会出现序列化报错

```
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
```
