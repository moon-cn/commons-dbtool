package cn.moon.dbtool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SqlBuilder {

    StringBuilder sql = new StringBuilder();

    List<Object> paramList = new ArrayList<>();



    public SqlBuilder(String sql) {
        this.sql.append(sql);
    }

    public SqlBuilder append(String sql, Object... params){
        this.sql.append(sql);

        paramList.addAll(Arrays.asList(params));
        return this;
    }

    public SqlBuilder append(boolean condition, String sql, Object... params){
        if(!condition){
            return this;
        }
        this.sql.append(sql);

        Collections.addAll(paramList, params);
        return this;
    }

    public Object[] getParamArray() {
        return paramList.toArray();
    }

    public String getSql(){
        return sql.toString();
    }
}
