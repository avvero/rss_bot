package com.avvero.rss_bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Avvero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventItem {

    private String title;
    private String link;
    private String description;
    private LocalDateTime pubDate;

}
