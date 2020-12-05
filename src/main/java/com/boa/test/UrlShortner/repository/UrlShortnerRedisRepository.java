package com.boa.test.UrlShortner.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShortnerRedisRepository implements UrlShortnerRepository {
    RedisTemplate redisTemplate;

    public UrlShortnerRedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveShortUrl(String shortUrlId, String longUrl) {
        redisTemplate.opsForValue().set(shortUrlId, longUrl);
    }

    @Override
    public String getLongUrl(String shortUrlId) {
        return (String) redisTemplate.opsForValue().get(shortUrlId);
    }
}
