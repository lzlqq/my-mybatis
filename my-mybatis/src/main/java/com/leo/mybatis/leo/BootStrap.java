package com.leo.mybatis.leo;

import com.leo.mybatis.beans.Test;
import com.leo.mybatis.leo.config.LeoConfiguration;
import com.leo.mybatis.leo.executor.ExecutorFactory;
import com.leo.mybatis.leo.session.LeoSqlSession;
import com.leo.mybatis.leo.test.TestMapper;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class BootStrap {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        LeoConfiguration configuration = new LeoConfiguration();
        configuration.setScanPath("com.leo.mybatis.leo.test");
        configuration.build();
        LeoSqlSession sqlSession = new LeoSqlSession(configuration, ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHING.name(), configuration));
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }
}
