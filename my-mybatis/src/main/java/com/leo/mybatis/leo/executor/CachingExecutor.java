package com.leo.mybatis.leo.executor;

import com.leo.mybatis.leo.config.LeoConfiguration;
import com.leo.mybatis.leo.config.MapperRegistry;
import com.leo.mybatis.leo.statement.StatementHandler;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class CachingExecutor implements Executor {
    private LeoConfiguration configuration;
    private Executor delegate;

    private Map<String, Object> localCache = new HashMap<>();

    public CachingExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CachingExecutor(LeoConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperRegistry.MapperData mapperData, Object parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        Object result = localCache.get(mapperData.getSql());
        if (null != result) {
            System.out.println("缓存命中");
            return (T) result;
        }
        result = (T) delegate.query(mapperData, parameter);
        localCache.put(mapperData.getSql(), result);
        return (T) result;
    }
}
