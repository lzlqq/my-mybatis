package com.leo.mybatis.my;

import java.lang.reflect.Proxy;

/**
 * Created by LSH7120 on 2019/2/26.
 */
public class LeoSqlSession {
    private Executor executor =new SimpleExecutor();

    public <T> T selectOne(String statement,Object parameter) {
        return executor.query(statement,parameter);
    }

    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(),
                new Class[]{type},
                new MapperProxy(this, type));
    }

}
