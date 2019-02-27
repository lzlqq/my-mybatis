package com.leo.mybatis.leo.result;

import com.leo.mybatis.leo.config.LeoConfiguration;
import com.leo.mybatis.leo.config.MapperRegistry;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class ResultSetHandler {
    private final LeoConfiguration configuration;

    public ResultSetHandler(LeoConfiguration configuration) {
        this.configuration = configuration;
    }

    public <T> T handle(PreparedStatement pstmt, MapperRegistry.MapperData mapperData) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object resultObj = new DefaultObjectFactory().create(mapperData.getType());
        ResultSet rs = pstmt.getResultSet();
        if (rs.next()) {
            int i = 0;
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, rs, i);
            }
        }
        return (T) resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field, rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        Class<?> type = field.getType();
        if (Integer.class == type) {
            return rs.getInt(field.getName());
        }
        if (String.class == type) {
            return rs.getString(field.getName());
        }
        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
