package com.avvero.rss_bot.dto.bf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fxdev-belyaev-ay
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationAccount {

    private Boolean isGroup;
    private String id;
    private String name;

}
