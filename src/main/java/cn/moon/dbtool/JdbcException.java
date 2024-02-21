package cn.moon.dbtool;

import java.sql.SQLException;

public class JdbcException extends RuntimeException{


    public JdbcException(Exception e) {
        super(e);
    }
    public JdbcException(SQLException e) {
        super(e);
        this.code = e.getErrorCode();
    }

    public JdbcException(String msg) {
        super(msg);
    }


    int code;

    public int getCode() {
        return code;
    }
}
