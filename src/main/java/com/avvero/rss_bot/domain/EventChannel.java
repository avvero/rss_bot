package com.avvero.rss_bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Avvero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventChannel {

    private String title;
    private String link;
    private String description;

}
