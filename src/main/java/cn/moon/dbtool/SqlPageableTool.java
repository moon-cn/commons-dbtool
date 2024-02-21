package cn.moon.dbtool;


import java.util.UUID;

/**
 * pageable sql tool
 *
 * base indexed on zero
 */
class SqlPageableTool {

    public static String getPageSql(int type, String sql, int page, int rows) {
        switch (type) {
            case Config.DB_TYPE_ORACLE:
                return getPageSqlForOracle(sql, page, rows);
            case Config.DB_TYPE_MYSQL:
                return getPageSqlForMysql(sql, page, rows);
            case Config.DB_TYPE_DB2:
                return getPageSqlForDb2(sql, page, rows);
            case Config.DB_TYPE_POSTGRES:
                return getPageSqlForPostgresql(sql, page, rows);
        }

        throw new JdbcException("not support database type " + type);
    }

    public static String getCountSql(String sql) {
        String randomAlias = "tmp_table_" + UUID.randomUUID().toString().replace("-","");

        return "select count(*) from (" + sql + ")  " + randomAlias;
    }

    public static String getPageSqlForOracle(String sql, int page, int rows) {

        return " select * " +
                " from " +
                " ( " +
                " select " +
                " rownum as rownum_, t.*  " +
                " from " +
                " ( " +
                sql +
                " ) t " +
                " where " +
                " rownum <= " +
                getOffsetNum2(page, rows) +
                " ) tt " +
                " where " +
                " tt.rownum_ > " +
                getOffsetNum(page, rows);
    }


    public static String getPageSqlForDb2(String sql, int page, int rows) {
        return "select * from " +
                " ( " +
                " select B.*, rownumber() over() as rn from " +
                " ( " +
                sql +
                " ) as b" +
                " ) as a where a.rn between " +
                getOffsetNum(page, rows) +
                " and " +
                getOffsetNum2(page, rows);

    }


    public static String getPageSqlForPostgresql(String sql, int page, int rows) {
        return sql + " limit " +
                rows +
                " offset " +
                getOffsetNum(page, rows);
    }


    public static String getPageSqlForMysql(String sql, int page, int rows) {
        return sql + " limit " +
                getOffsetNum(page, rows) +
                " , " +
                rows;
    }

    private static int getOffsetNum(int page, int rows) {
        return page * rows;
    }

    private static int getOffsetNum2(int page, int rows) {
        return (page + 1) * rows;
    }

}
