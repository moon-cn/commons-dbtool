package cn.moon.dbtool.dbutil;

import cn.moon.dbtool.Converters;
import org.apache.commons.dbutils.GenerousBeanProcessor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class MyBeanProcessor extends GenerousBeanProcessor {


    @Override
    protected void callSetter(Object target, PropertyDescriptor prop, Object value) throws SQLException {
        try {
            super.callSetter(target, prop, value);
        } catch (SQLException e) {
            this.callCustomSetter(target, prop, value);
        }
    }

    private void callCustomSetter(Object target, PropertyDescriptor prop, Object value) throws SQLException {
        final Method setter = getWriteMethod(target, prop, value);
        try {
            Converters cvt = Converters.getInstance();
            Class<?> type = prop.getPropertyType();
            value = cvt.convert(type, prop, value);
            if(value == null){
                return;
            }


            if(type.isAssignableFrom(value.getClass()) ){
                setter.invoke(target, value);
            }
        } catch (final IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            throw new SQLException("Cannot set " + prop.getName() + ": " + e.getMessage());
        }
    }
}
