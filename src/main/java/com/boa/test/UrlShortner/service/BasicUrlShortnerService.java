package com.boa.test.UrlShortner.service;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BasicUrlShortnerService implements UrlShortnerService {
    @Override
    public String shortenUrl(String longUrl, String domainUrl) {
        final String shortUrlId = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();

        return domainUrl + shortUrlId;
    }
}
