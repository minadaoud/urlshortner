package com.boa.test.UrlShortner.controller;

import com.boa.test.UrlShortner.domain.ShortUrlRequest;
import com.boa.test.UrlShortner.service.UrlShortnerService;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UrlShortnerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerController.class);
    private UrlShortnerService urlShortnerService;

    public UrlShortnerController(UrlShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    @RequestMapping(value = "/short-url", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<String> shortenUrl(@RequestBody final ShortUrlRequest longUrl, HttpServletRequest request) {
        LOGGER.info("Received url to shorten: " + longUrl.getLongUrl());
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});

        if (urlValidator.isValid(longUrl.getLongUrl())) {
            String localDomainUrl = request.getRequestURL().toString();
            String shortUrl = urlShortnerService.shortenUrl(longUrl.getLongUrl(), localDomainUrl);
            LOGGER.info("Url shortened to: " + shortUrl);
            return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
        }
        LOGGER.info("Invalid long url : " + longUrl.getLongUrl());

        /*Here I am returning a basic text message with 400 HTTP status code. This could be enhanced by sending an
         object with internal error code*/
        return new ResponseEntity<>("Invalid long url! Please use a valid url", HttpStatus.BAD_REQUEST);
    }
}
