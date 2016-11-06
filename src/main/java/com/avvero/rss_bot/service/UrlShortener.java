package com.avvero.rss_bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Avvero
 */
@Service
public class UrlShortener {

    @Value("${google.api.urlshortener.url}")
    private String url;
    @Value("${google.api.urlshortener.key}")
    private String key;

    public String make(String longUrl) {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<ShortenerRequest> request = new HttpEntity(new ShortenerRequest(longUrl), createHeaders());
        ShortenerResponse response = restTemplate.postForObject(String.format(url, key), request,
                ShortenerResponse.class);
        return response.getId();
    }

    HttpHeaders createHeaders() {
        return new HttpHeaders() {
            {
                set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            }
        };
    }

}
