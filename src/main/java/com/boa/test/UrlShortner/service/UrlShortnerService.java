package com.boa.test.UrlShortner.service;

public interface UrlShortnerService {
    String shortenUrl(String longUrl, String domainUrl);
    String getLongUrl(String shortUrlId);
}
