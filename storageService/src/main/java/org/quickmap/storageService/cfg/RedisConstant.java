package org.quickmap.storageService.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConstant {

    @Value("${spring.redis.poolSize}")
    private int poolSize;

    @Value("${spring.redis.fsIndexName}")
    private String fsIndexName;

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

    public String getFsIndexName() {
        return fsIndexName;
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
