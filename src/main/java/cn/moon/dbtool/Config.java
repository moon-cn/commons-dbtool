package cn.moon.dbtool;

import lombok.Data;

@Data
public class Config {

    // 默认，不处理 （bean结果集会自动）
    public static final int NAMING_STRATEGY_NONE = -1;

    // Java字段以驼峰为主， 数据库字段则以下划线分割
    public static final int NAMING_STRATEGY_IMPROVED = 0;

    public static final int DB_TYPE_MYSQL = 0;
    public static final int DB_TYPE_ORACLE = 1;
    public static final int DB_TYPE_DB2 = 2;
    public static final int DB_TYPE_POSTGRES = 3;


    private int namingStrategy = NAMING_STRATEGY_IMPROVED;

    private int dbType = DB_TYPE_MYSQL;
}
