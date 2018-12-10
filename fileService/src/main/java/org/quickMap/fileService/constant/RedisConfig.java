package org.quickMap.fileService.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    @Value("${spring.redis.poolSize}")
    private int poolSize;

    @Value("${spring.redis.indexName}")
    private String indexName;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.password}")
    private String password;


    public int getPoolSize() {
        return poolSize;
    }

    public String getIndexName() {
        return indexName;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getDatabase() {
        return database;
    }

    public String getPassword() {
        return password;
    }

}
