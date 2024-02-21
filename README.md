# commons-dbtool

#### Intro
The Moon Commons-DbTool package is a set of
Java utility classes for easing JDBC development.
Support Spring data pageable

depends on https://github.com/apache/commons-dbutils

#### Where can I get the latest release?
You can pull it from the central Maven repositories:
```
 <dependency>
    <groupId>io.github.moon-cn</groupId>
    <artifactId>commons-dbtool</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### How to Use


```
  DbTool db = new DbTool(datasource);
  List<User> userList = db.findAll(User.clss, sql, params);
  Page<User> page = db.finAll(User.clss, pageable, sql, params):
  User user = db.findOne(User.class, sql, params);
```


#### 扩展的功能
- 默认下划线会转换为驼峰 
- 支持枚举，且支持数据库数字和字符，如果数据库是数字，判断枚举下标，如果是字符串，判断name
- 支持常见日期格式
- 支持List<String>
- 支持自定义