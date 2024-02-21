package cn.moon.dbtool;

import org.apache.commons.dbutils.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class TableGenerator {


    public static String generateCreateTableSql(Class<?> clazz, String tableName) {
        StringBuilder sb = new StringBuilder();


        sb.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (\n");

        // 获取类以及父类的所有字段
        List<Field> allFields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                // 排除静态字段
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                allFields.add(field);
            }
            currentClass = currentClass.getSuperclass();
        }

        // 生成字段定义
        List<String> fieldDefinitions = new ArrayList<>();
        for (Field field : allFields) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();

            fieldDefinitions.add(fieldName + " " + getSqlType(fieldType));
        }

        // 拼接字段定义
        sb.append(String.join(",\n", fieldDefinitions));

        sb.append("\n);");

        return sb.toString();
    }

    @Column
    public static String getSqlType(String fieldType) {
        switch (fieldType.toLowerCase()) {
            case "byte":
            case "short":
            case "int":
            case "integer":
                return "INT";
            case "long":
                return "BIGINT";
            case "float":
                return "FLOAT";
            case "double":
                return "DOUBLE";
            case "boolean":
                return "BOOLEAN";
            case "char":
            case "string":
                return "VARCHAR(255)";
            case "date":
                return "datetime(6)";

            // support collection, convert to string
            case "list":
            case "set":
                return "longtext";
            default:
                throw new JdbcException("not support field type " + fieldType);
        }
    }
}