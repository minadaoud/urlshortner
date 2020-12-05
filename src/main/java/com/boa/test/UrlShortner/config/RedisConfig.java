package com.boa.test.UrlShortner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class RedisConfig {
    private RedisServer redisServer;
    @Value("${spring.redis.port}") int redisPort;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer  = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis(){
        redisServer.stop();
    }
}
