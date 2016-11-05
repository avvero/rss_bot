package com.avvero.rss_bot.service;

import lombok.Data;

/**
 * @author fxdev-belyaev-ay
 */
@Data
public class Token {

    private String token_type;
    private Integer expires_in;
    private Integer ext_expires_in;
    private String access_token;

}
