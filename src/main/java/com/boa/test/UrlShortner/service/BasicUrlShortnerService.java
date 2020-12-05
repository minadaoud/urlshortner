package com.boa.test.UrlShortner.service;

import com.boa.test.UrlShortner.repository.UrlShortnerRepository;
import com.google.common.hash.Hashing;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BasicUrlShortnerService implements UrlShortnerService {
    UrlShortnerRepository urlShortnerRepository;

    public BasicUrlShortnerService(UrlShortnerRepository urlShortnerRepository) {
        this.urlShortnerRepository = urlShortnerRepository;
    }

    @Override
    public String shortenUrl(String longUrl, String domainUrl) {
        final String shortUrlId = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        urlShortnerRepository.saveShortUrl(shortUrlId, longUrl);

        return domainUrl + "/" + shortUrlId;
    }

    @Override
    public String getLongUrl(String shortUrlId) {
        return urlShortnerRepository.getLongUrl(shortUrlId);
    }
}
