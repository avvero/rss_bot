package com.avvero.rss_bot.dto.bf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fxdev-belyaev-ay
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Button {

    private String type;
    private String title;
    private String value;

}
