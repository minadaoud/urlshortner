package com.boa.test.UrlShortner.repository;

public interface UrlShortnerRepository {
    void saveShortUrl(String id, String longUrl);
    String getLongUrl(String id);
}
