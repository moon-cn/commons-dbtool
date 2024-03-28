package cn.moon;

import cn.moon.dbtool.DbTool;
import cn.moon.dbtool.meta.Column;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MetaTest {

    private static DbTool db = new DbTool(DatasourceFactory.get());


    @BeforeAll
    public static void init() {
        db.execute("DROP TABLE IF EXISTS user");
        db.execute("CREATE TABLE user  (\n" +
                   "  id int NOT NULL,\n" +
                   "  name varchar(255) ,\n" +
                   "  age int ,\n" +
                   "  sex int ,\n" +
                   "  sex_str varchar(10) ,\n" +
                   "  mother_name varchar(255),\n" +
                   "  parent_ages varchar(255),\n" +
                   "  fav_food varchar(255) ,\n" +
                   "  ts timestamp ,\n" +
                   "  birthday date ,\n" +
                   "  PRIMARY KEY (id) \n" +
                   ")");
        db.execute("INSERT INTO user VALUES (1, 'Jack', 23, 1,'male','M-Jack', '50,52',  'apple,fish,egg', '2024-02-05 22:41:05', '2024-02-05')");
        db.execute("INSERT INTO user VALUES (2, 'Mike', 26, 0,'female','M-Mike','60,64', 'rice,cake', '2024-01-05 22:41:05', '2024-02-06')");
    }


    @Test
    public void test() throws SQLException {
        List<Column> columns = db.getColumns( "select id , name as 名字 from user");
        Assertions.assertTrue(columns.size() == 2);
        Assertions.assertTrue(columns.stream().map(Column::getName).collect(Collectors.toList()).contains("id"));
        Assertions.assertTrue(columns.stream().map(Column::getLabel).collect(Collectors.toList()).contains("名字"));
    }


}
