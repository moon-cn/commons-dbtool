package io.github.moon1949;

import cn.moon.dbtool.DbTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifyTest {

    private String sql = "select * from user where 1=1 ";
    private static DbTool db = new DbTool(DatasourceFactory.get());
    @BeforeEach
    public  void init() {
        db.execute("DROP TABLE IF EXISTS user");
        db.execute("CREATE TABLE user  (\n" +
                "  id int NOT NULL,\n" +
                "  name varchar(255) ,\n" +
                "  age int ,\n" +
                "  mother_name varchar(255),\n" +
                "  PRIMARY KEY (id) \n" +
                ")");
    }


    @Test
    public void insertMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name","zhang");
        int row = db.insert("user", map);

        Assertions.assertEquals(row, 1);

        List<Map<String, Object>> list = db.findAll(sql);
        Assertions.assertEquals(list.size(), 1);

        Map<String, Object> user = db.findOne(sql + "and id=1");
        Assertions.assertEquals(user.get("name"), "zhang");


        map.put("name","lisi");
        map.put("motherName","myMother");

        int updateRow = db.updateById("user", map);
        Assertions.assertEquals(updateRow, 1);
        Map<String, Object> user2 = db.findOne(sql + "and id=1");
        Assertions.assertEquals(user2.get("name"), "lisi");
        Assertions.assertEquals(user2.get("motherName"), "myMother");

    }


}
