package com.avvero.rss_bot.entity.bf;

import lombok.Data;

import java.util.List;

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
    private List<AttachmentEntry> content;

}
