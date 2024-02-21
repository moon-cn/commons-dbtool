package cn.moon.dbtool;

import cn.moon.dbtool.converter.*;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class Converters {


    private final List<Converter> converters = new ArrayList<>();

    private static Converters INS;

    public synchronized static Converters getInstance() {
        if(INS == null){
            INS = new Converters();
        }
        return INS;
    }

    private Converters() {
        register(new TimestampToLocalDateTimeConverter());
        register(new TimestampToLocalDateConverter());
        register(new DateToLocalDateConverter());
        register(new DateToLocalDateTimeConverter());
        register(new IntegerToEnumConverter());
        register(new StringToEnumConverter());
        register(new StringToListIntConverter());
        register(new StringToListConverter());

    }

    public void register(Converter converter) {
        converters.add(converter);
    }


    public Object convert(Class<?> attrType, PropertyDescriptor prop, Object value) {
        if (value == null) {
            return null;
        }
        Class<?> dbDataType = value.getClass();


        Converter cvt = getConverter(dbDataType, prop);


        if (cvt != null) {
            return cvt.convertTo(value, attrType);
        }


        return value;
    }

    private Converter getConverter(Class<?> dbDataType, PropertyDescriptor targetProp) {
        for (Converter converter : converters) {
            boolean match = converter.match(dbDataType, targetProp);
            if (match) {
                return converter;
            }
        }
        return null;
    }







}
