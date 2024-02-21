package cn.moon.dbtool.converter;

import cn.moon.dbtool.Converter;

import java.beans.PropertyDescriptor;
import java.sql.Date;
import java.time.LocalDate;

public class DateToLocalDateConverter implements Converter {

    @Override
    public boolean match(Class<?> dbData, PropertyDescriptor target) {
        Class<?> type = target.getPropertyType();
        return dbData == Date.class && type == LocalDate.class;
    }

    @Override
    public Object convertTo(Object dbData, Class<?> targetType) {
        Date d = (Date) dbData;
        return d.toLocalDate();
    }

}
