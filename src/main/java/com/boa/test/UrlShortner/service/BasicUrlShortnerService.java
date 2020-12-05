package com.boa.test.UrlShortner.service;

import com.boa.test.UrlShortner.repository.UrlShortnerRepository;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BasicUrlShortnerService implements UrlShortnerService {
    UrlShortnerRepository urlShortnerRepository;

    public BasicUrlShortnerService(UrlShortnerRepository urlShortnerRepository) {
        this.urlShortnerRepository = urlShortnerRepository;
    }

    @Override
    public String shortenUrl(String longUrl, String requestUrl) {
        final String shortUrlId = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        urlShortnerRepository.saveShortUrl(shortUrlId, longUrl);

        return formatLocalURLFromShortener(requestUrl) + shortUrlId;
    }

    @Override
    public String getLongUrl(String shortUrlId) {
        return urlShortnerRepository.getLongUrl(shortUrlId);
    }

    /**
     * Private method to get the base Url from the request URL.
     */
    private String formatLocalURLFromShortener(String requestUrl) {
        String[] addressComponents = requestUrl.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            if (i == 0) {
                sb.append(addressComponents[i]);
                sb.append("//");
            } else {
                sb.append(addressComponents[i]);
            }
        }
        sb.append('/');
        return sb.toString();
    }
}
