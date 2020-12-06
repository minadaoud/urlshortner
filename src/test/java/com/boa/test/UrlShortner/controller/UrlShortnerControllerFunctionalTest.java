package com.boa.test.UrlShortner.controller;


import com.boa.test.UrlShortner.domain.ShortUrlRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UrlShortnerControllerFunctionalTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private static final ShortUrlRequest SHORT_URL_REQUEST = new ShortUrlRequest("http://www.google.com");
    private static final String LOCAL_BASE_URL = "http://localhost:";
    private static final String SHORT_URL_ID = "4170157c";

    @Test
    public void testShorteningUrlSuccessScenario() throws Exception {
        String expectedShortUrl = LOCAL_BASE_URL + port + "/" + SHORT_URL_ID;
        String shortUrl = this.restTemplate.postForObject(LOCAL_BASE_URL + port + "/short-url", SHORT_URL_REQUEST, String.class);

        assertEquals(expectedShortUrl, shortUrl);
    }

    @Test
    public void testRedirectSuccessScenario() throws Exception {
        String shortUrl = LOCAL_BASE_URL + port + "/" + SHORT_URL_ID;

        //First we insert a short url id
        this.restTemplate.postForObject(LOCAL_BASE_URL + port + "/short-url", SHORT_URL_REQUEST, String.class);

        String longUrl = this.restTemplate.getForObject(shortUrl, String.class);

        // A cheap and inefficient way to make sure that we have been redirected to google.com
        assertTrue(longUrl.contains("google.com"));
    }
}