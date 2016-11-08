package com.avvero.rss_bot.dto.rss;

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
