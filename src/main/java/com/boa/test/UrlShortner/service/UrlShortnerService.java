package com.boa.test.UrlShortner.service;

public interface UrlShortnerService {
    String shortenUrl(String longUrl, String requestUrl);
    String getLongUrl(String shortUrlId);
}
