package com.leo.mybatis.leo.session;

import com.leo.mybatis.leo.config.LeoConfiguration;
import com.leo.mybatis.leo.config.MapperRegistry;
import com.leo.mybatis.leo.executor.Executor;
import com.leo.mybatis.leo.mapper.MapperProxy;
import com.leo.mybatis.leo.test.TestMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class LeoSqlSession {
    private LeoConfiguration cofiguration;
    private Executor executor;

    //关联起来
    public LeoSqlSession(LeoConfiguration configuration, Executor executor) {
        this.cofiguration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(),
                new Class[]{type},
                new MapperProxy(this,type));
    }

    public LeoConfiguration getCofiguration() {
        return cofiguration;
    }

    public <T> T selectOne(MapperRegistry.MapperData mapperData, Object parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //  在构造器中指定的executor是CachingExecutor,CachinExecutor构造器中传入SimpleExecutor
        //  CachinExecutor委托SimpleExecutor，执行具体的查询，他主要做缓存用
        //  SimpleExecutor中的操作分两步：1.准备sql,执行查询，2.组装查询结果
        //  StatementHandler,ResultSetHandler
        return executor.query(mapperData,parameter);
    }
}
