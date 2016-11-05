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
public class Event {

    private String sourceUrl;
    private EventChannel channel;
    private EventItem item;

}
