package com.leo.mybatis.my;

/**
 * Created by LSH7120 on 2019/2/26.
 */
public class BootStrap {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        LeoSqlSession leoSqlSession=new LeoSqlSession();
        TestMapper testMapper = leoSqlSession.getMapper(TestMapper.class);
        System.out.println(testMapper.getClass());
        System.out.println(testMapper.selectByPrimaryKey(1));
    }
}
