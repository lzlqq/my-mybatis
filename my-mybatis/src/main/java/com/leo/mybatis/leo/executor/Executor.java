package com.leo.mybatis.leo.executor;

import com.leo.mybatis.leo.config.MapperRegistry;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public interface Executor {

    <T> T query(MapperRegistry.MapperData mapperData, Object parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
