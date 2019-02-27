package com.leo.mybatis.leo.executor;

import com.leo.mybatis.leo.config.LeoConfiguration;

/**
 * Created by LSH7120 on 2019/2/27.
 */
public class ExecutorFactory {
    private static final String SIMPLE = "SIMPLE";
    private static final String CACHING = "CACHING";

    public static Executor getDefault(LeoConfiguration configuration) {
        return get(SIMPLE, configuration);
    }

    public static Executor get(String key, LeoConfiguration configuration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new SimpleExecutor(configuration);
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new CachingExecutor(new SimpleExecutor(configuration));
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType {
        SIMPLE, CACHING;
    }
}
