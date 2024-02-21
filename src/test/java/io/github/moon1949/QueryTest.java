package io.github.moon1949;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cn.moon.dbtool.DbTool;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class QueryTest {

    private static DbTool db = new DbTool(DatasourceFactory.get());

    private static final int USER1_ID = 1;
    private static final String USER1_Name = "Jack";
    private String sql = "select * from user";

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
    public void findOne() {
        User user = db.findOne(User.class, "select * from user where id=1");

        print(user);
        Assertions.assertNotNull(user.getMotherName());

        Assertions.assertNotNull(user.getSex());
        Assertions.assertNotNull(user.getSexStr());

        List<Integer> parentAges = user.getParentAges();

        Integer age = parentAges.get(0);
        Assertions.assertEquals(age,50);
    }

    @Test
    public void findBeanMap() {
        Map<Integer, User> beanMap = db.findBeanMap(User.class, "select * from user");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(beanMap));

        Assertions.assertEquals(2, beanMap.size());
        Assertions.assertEquals(beanMap.get(USER1_ID).getName(), USER1_Name);

    }

    @Test
    public void findOneMap() {
        Map<String, Object> map1 = db.findOne("select * from user where id=" + USER1_ID);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(map1));
        Assertions.assertEquals(map1.get("name"), USER1_Name);
        Assertions.assertTrue(map1.containsKey("motherName"));


    }

    @Test
    public void findKeyed() {

        Map<Integer, Map<String, Object>> keyed1 = db.findKeyed(sql);


    }

    @Test
    public void findDict() {
        Map<Object, Object> dict = db.findDict(sql);

        print(dict);
        Assertions.assertEquals(dict.get(USER1_ID), USER1_Name);
    }

    @Test
    public void findAllMap() {
        List<Map<String, Object>> list = db.findAll(sql);

        print(list);

    }

    @Test
    public void findAll() {
        List<User> list = db.findAll(User.class,sql);

        print(list);


        User user = list.get(0);
        Assertions.assertNotNull(user.getMotherName());

    }




    private void print(Object obj) {
        System.out.println();
        System.out.println("-------------");
        System.out.println(obj);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(obj));
        System.out.println("-------------");
        System.out.println();

    }

    @Data
    public static class User {
        Integer id;
        String name;
        String motherName;


        List<Integer> parentAges;
        List<String> favFood;

        Sex sex;
        Sex sexStr;


        public enum Sex {
            male,
            female

        }

    }


}
