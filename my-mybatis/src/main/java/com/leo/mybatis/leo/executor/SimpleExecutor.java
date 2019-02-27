package com.leo.mybatis.leo.executor;

import com.leo.mybatis.leo.config.LeoConfiguration;
import com.leo.mybatis.leo.config.MapperRegistry;
import com.leo.mybatis.leo.statement.StatementHandler;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class SimpleExecutor implements Executor {
    private LeoConfiguration configuration;

    public SimpleExecutor(LeoConfiguration configuration) {
        this.configuration = configuration;
    }

    public LeoConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(LeoConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperRegistry.MapperData mapperData, Object parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler =new StatementHandler(configuration);
        return (T) handler.query(mapperData,parameter);
    }
}
