package com.boa.test.UrlShortner.domain;


public class ShortUrlRequest {
    private String longUrl;

    public ShortUrlRequest() {
    }

    public ShortUrlRequest(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
