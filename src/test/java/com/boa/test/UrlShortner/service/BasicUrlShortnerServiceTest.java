package com.boa.test.UrlShortner.service;

import com.boa.test.UrlShortner.repository.UrlShortnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class BasicUrlShortnerServiceTest {
    @Mock
    UrlShortnerRepository urlShortnerRepository;
    @InjectMocks
    private BasicUrlShortnerService urlShortnerService;

    private static final String LONG_URL = "http://www.google.com";
    private static final String SHORT_URL = "http://localhost:8080/4170157c";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatingShortUrl() {
        String domainUrl = "http://localhost:8080/short-url";
        String shortUrlId = "4170157c";

        String shortUrl = urlShortnerService.shortenUrl(LONG_URL, domainUrl);

        verify(urlShortnerRepository, atMostOnce()).saveShortUrl(shortUrlId, LONG_URL);

        assertEquals(SHORT_URL, shortUrl);
    }

    @Test
    void testRetrievingShortUrl() {
        when(urlShortnerService.getLongUrl(SHORT_URL)).thenReturn(LONG_URL);
        verify(urlShortnerRepository, atMostOnce()).getLongUrl(LONG_URL);

        assertEquals(LONG_URL, urlShortnerService.getLongUrl(SHORT_URL));
    }
}