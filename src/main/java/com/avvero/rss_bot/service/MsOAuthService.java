package com.avvero.rss_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author fxdev-belyaev-ay
 */
@Service
public class MsOAuthService {

    @Autowired
    private Environment env;

    public Token token() {
        String url = env.getRequiredProperty("ms.oauth2.token.url");
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.add("grant_type", env.getRequiredProperty("ms.oauth2.token.grant_type"));
        params.add("client_id", env.getRequiredProperty("ms.oauth2.token.client_id"));
        params.add("client_secret", env.getRequiredProperty("ms.oauth2.token.client_secret"));
        params.add("scope", env.getRequiredProperty("ms.oauth2.token.scope"));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Token> entry = restTemplate.postForEntity(url, params, Token.class);
        return entry.getBody();
    }

}
