package org.quickMap.dataService.cfg;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * redis缓存配置
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> redis = new RedisTemplate<>();
        redis.setConnectionFactory(redisConnectionFactory);
        redis.setEnableTransactionSupport(true);
        redis.afterPropertiesSet();
        return redis;
    }

}