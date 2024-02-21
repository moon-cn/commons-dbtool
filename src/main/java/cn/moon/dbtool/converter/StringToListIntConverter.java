package cn.moon.dbtool.converter;

import cn.moon.dbtool.Converter;
import cn.moon.dbtool.Helpers;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;


/**
 * convert to List String
 */
public class StringToListIntConverter implements Converter {
    @Override
    public boolean match(Class<?> dbData, PropertyDescriptor target ) {
        boolean isList = List.class.isAssignableFrom(target.getPropertyType());
        return isList && dbData == String.class && Helpers.getFirstGeneric(target) == Integer.class;
    }




    @Override
    public Object convertTo(Object dbData, Class<?> targetType) {
        List<Integer> list = new ArrayList<>();

        String str = (String) dbData;
        if (!str.isEmpty()) {
            String[] arr = str.split(",");

            for (String s : arr) {
                int i = Integer.parseInt(s);
                list.add(i);
            }

        }
        return list;
    }


}
