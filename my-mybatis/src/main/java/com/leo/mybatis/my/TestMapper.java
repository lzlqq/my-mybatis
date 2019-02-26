package com.leo.mybatis.my;


import com.leo.mybatis.beans.Test;

public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}