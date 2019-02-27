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

    /**
     * 此处的技巧：
     * Proxy生成的动态代理实现的是type的接口
     * Proxy生成的动态代理类中的处理器是MapperProxy
     * 而调用生成动态代理的类和动态代理的接口，作为参数传给了动态代理处理器类
     * 调用动态代理的类恰好是SqlSession，因而后续操作数据库时又会回到SqlSession中
     * 这个动态代理类的核心作用是映射mapperInterface和SQL关系，这样SqlSession就能调用Executor执行DB操作了
     * 其实mapperInterface接口只是个标志，并不是真正的接口作用
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(),
                new Class[]{type},
                new MapperProxy(this, type));
    }

}
