package com.leo.mybatis.leo.mapper;

import com.leo.mybatis.leo.config.MapperRegistry;
import com.leo.mybatis.leo.session.LeoSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class MapperProxy<T> implements InvocationHandler {
    private final LeoSqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(LeoSqlSession leoSqlSession, Class<T> mapperInterface) {
        this.sqlSession = leoSqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistry.MapperData mapperData=sqlSession.getCofiguration()
                .getMapperRegistry()
                .get(method.getDeclaringClass().getName()+"."+method.getName());
        if(null!=mapperData){
            System.out.println(String.format("SQL [%s],parameter [%s] ",mapperData.getSql(),mapperData.getType()));
            return sqlSession.selectOne(mapperData,String.valueOf(args[0]));
        }
        return method.invoke(proxy, args);
    }


}
