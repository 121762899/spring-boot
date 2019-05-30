package com.spring.boot.entity.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Configuration properties for Redis.（redis的属性配置类）
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
    public class RedisConfig { 
    private String host;
    private Integer maxTotal;
    private Integer maxIdle;
    private Long maxWaitMillis;
    private Boolean testOnBorrow;
    private Integer port;

}

