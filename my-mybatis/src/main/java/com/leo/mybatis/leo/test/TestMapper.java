package com.leo.mybatis.leo.test;


import com.leo.mybatis.beans.Test;

public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}