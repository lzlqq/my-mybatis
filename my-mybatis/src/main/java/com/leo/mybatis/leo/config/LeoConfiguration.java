package com.leo.mybatis.leo.config;

import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class LeoConfiguration {
    private String scanPath;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public void build() {
        if (scanPath == null || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required");
        }
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }
}
