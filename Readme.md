# maintenance(设备维修)

### SpringBoot 3.x + Satoken + JDK 17 + MybatisPlus 3.5.x + Redis 3.x + Minio 8.5.x + Elasticsearch 7.x + Xxl-job + Sharding-jdbc 5.4.x

> 模块解析

* maintenance-common: 公共模块
* maintenance-core: 系统代码模块
* maintenance-es: elasticserach 模块
* maintenance-mbg: 权限 + mybatis 模块
* maintenance-file: 文件上传模块
* maintenance-forest: 三方对接模块
* maintenance-sms: 短信发送模块
* maintenance-xxljob: 动态定时任务模块

执行 doc/sql 下的 maintenance.sql 文件，初始租户为测试，账号为 admin， 密码为 123456

doc/layout 为布局，不需要可以自行删除 maintenance-core/src/main/java/com/device/core/column 下的所有代码

> 需要注意，存入redis的实体类，其中LocalDate，LocalDateTime必须添加以下注解，否则会出现序列化报错

LocalDate
```
@JsonFormat(pattern = "yyyy-MM-dd")
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
```

LocalDateTime
```
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
```
> 定时任务无法走租户模式，若为租户模式走定时任务，则需要手动传入租户id，或者直接在 DeviceTenantHandler 忽略此表

> 若使用 sharding-jdbc 分表也需要直接在 DeviceTenantHandler 忽略此表
