package com.avvero.rss_bot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Avvero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortenerRequest {
    private String longUrl;
}
