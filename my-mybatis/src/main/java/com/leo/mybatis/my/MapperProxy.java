package com.leo.mybatis.my;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by LSH7120 on 2019/2/26.
 */
public class MapperProxy<T> implements InvocationHandler {
    private final LeoSqlSession leoSqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(LeoSqlSession leoSqlSession, Class<T> mapperInterface) {
        this.leoSqlSession = leoSqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(TestMapperXml.nameSpace)){
            String sql = TestMapperXml.methodSqlMapping.get(method.getName());
            System.out.println(String.format("SQL [ %s ], parameter [%s] ",sql,args[0]));
            return  leoSqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return null;
    }
}
