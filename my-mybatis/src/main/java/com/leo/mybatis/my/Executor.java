package com.leo.mybatis.my;

/**
 * Created by LSH7120 on 2019/2/26.
 */
public interface Executor {

    <T> T query(String statement, Object parameter);
}
