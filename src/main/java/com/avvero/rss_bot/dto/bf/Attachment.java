package com.avvero.rss_bot.dto.bf;

import lombok.Data;

/**
 * @author fxdev-belyaev-ay
 */
@Data
public class Attachment {

    private String contentType;
    private String contentUrl;
    private String thumbnailUrl;
    private String filename;
    private String name;
    private AttachmentEntry content;

}
