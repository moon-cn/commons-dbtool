package cn.moon.dbtool;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Helpers {

    public static List<Map<String, Object>> camel(List<Map<String,Object>> list) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            list.set(i, Helpers.camel(map));
        }
        return list;
    }
    public static Map<String,Object> camel(Map<String,Object> map){
        Map<String,Object> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            String key = e.getKey();
            Object value = e.getValue();
            if(key.contains("_")){
                key = Helpers.camel(key);
            }

            newMap.put(key,value);

        }
        return newMap;
    }


    // 下划线转驼峰
    public static String camel(String columnName) {
        StringBuilder sb = new StringBuilder();
        boolean match = false;
        for (int i = 0; i < columnName.length(); i++) {
            char ch = columnName.charAt(i);
            if (match && ch >= 97 && ch <= 122)
                ch -= 32;
            if (ch != '_') {
                match = false;
                sb.append(ch);
            } else {
                match = true;
            }
        }
        return sb.toString();
    }
    public static String underline(String camelCase) {
        if(camelCase == null){
            return null;
        }
        StringBuilder snakeCase = new StringBuilder();

        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);

            if (Character.isUpperCase(c)) {
                snakeCase.append("_");
                snakeCase.append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }

        if(snakeCase.charAt(0) == '_'){
            snakeCase.deleteCharAt(0);
        }
        return snakeCase.toString();
    }

    public static void underline(Map<String, Object> map) {
        Set<String> keys = map.keySet();
        String[] keyArr = (String[])keys.toArray(new String[keys.size()]);
        String[] var3 = keyArr;
        int var4 = keyArr.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String key = var3[var5];
            String keyUnderline = underline(key);
            if (!keyUnderline.equals(key)) {
                Object v = map.get(key);
                map.put(keyUnderline, v);
                map.remove(key);
            }
        }

    }

    public static Type getFirstGeneric(PropertyDescriptor propertyDescriptor){
        Method readMethod = propertyDescriptor.getReadMethod();
        Type genericReturnType = readMethod.getGenericReturnType();

        if(genericReturnType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericReturnType;

            Type argument = pt.getActualTypeArguments()[0];

            return argument;
        }

        return null;
    }



}
