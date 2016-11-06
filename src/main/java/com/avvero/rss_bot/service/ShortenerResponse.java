package com.avvero.rss_bot.service;

import lombok.Data;

/**
 * @author Avvero
 */
@Data
public class ShortenerResponse {
    private String kind;
    private String id;
    private String longUrl;
}
