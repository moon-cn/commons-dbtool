# 数据库工具类

#### 介绍
依赖 https://github.com/apache/commons-dbutils

#### 安装
```
 <dependency>
    <groupId>io.github.moon-cn</groupId>
    <artifactId>commons-dbtool</artifactId>
    <version>1.x.x</version>
</dependency>
```
最新版查看地址: https://mvnrepository.com/artifact/io.github.moon-cn/commons-dbtool

#### 使用
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
