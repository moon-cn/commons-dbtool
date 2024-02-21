package cn.moon.dbtool.converter;

import cn.moon.dbtool.Converter;

import java.beans.PropertyDescriptor;

public class StringToEnumConverter implements Converter {
    @Override
    public boolean match(Class<?> dbData, PropertyDescriptor target) {
        return Enum.class.isAssignableFrom(target.getPropertyType()) && dbData == String.class;
    }

    @Override
    public Object convertTo(Object dbData, Class targetType) {
           return Enum.valueOf(targetType, (String) dbData);
    }
}
