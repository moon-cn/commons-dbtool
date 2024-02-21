package cn.moon.dbtool.converter;

import cn.moon.dbtool.Converter;

import java.beans.PropertyDescriptor;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateToLocalDateTimeConverter implements Converter {


    @Override
    public boolean match(Class<?> dbData, PropertyDescriptor target) {
        return dbData == Date.class && target.getPropertyType() == LocalDateTime.class;
    }

    @Override
    public Object convertTo(Object dbData, Class<?> targetType) {
        Date d = (Date) dbData;
        return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
    }

}
