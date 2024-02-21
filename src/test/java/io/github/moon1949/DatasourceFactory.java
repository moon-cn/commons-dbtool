package io.github.moon1949;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DatasourceFactory {
    public static DataSource get(){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true");
        ds.setPassword("123456");
        ds.setUser("root");
        return ds;
    }

}
