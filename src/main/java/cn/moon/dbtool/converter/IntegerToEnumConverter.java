package cn.moon.dbtool.converter;

import cn.moon.dbtool.Converter;

import java.beans.PropertyDescriptor;

public class IntegerToEnumConverter implements Converter {
    @Override
    public boolean match(Class<?> dbData, PropertyDescriptor target) {
        boolean isEnum = Enum.class.isAssignableFrom(target.getPropertyType());
        return isEnum && dbData == Integer.class;
    }

    @Override
    public Object convertTo(Object value, Class<?> targetType) {
        Object[] enumConstants = targetType.getEnumConstants();
        Integer index = (Integer) value;
        if (enumConstants.length > index) {
            return enumConstants[index];
        }

        return value;
    }
}
