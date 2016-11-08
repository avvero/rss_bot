package com.avvero.rss_bot.dto.bf;

import com.avvero.rss_bot.utils.LocalDateTimeStringDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Avvero
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversationMessage {

    private String id;
    private String type;
    private String text;
    private String locale;
    private String summary;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeStringDeserialize.class)
    private LocalDateTime timestamp;
    private String serviceUrl;
    private String channelId;
    private String action;
    private String topicName;
    private Boolean historyDisclosed;
    private List<ChannelAccount> membersAdded;
    private List<ChannelAccount> membersRemoved;
    private ConversationAccount conversation;
    private ChannelAccount from;
    private ChannelAccount recipient;
    private String attachmentLayout;
    private List<Attachment> attachments;
    private List<Map> entities;
    private ChannelData channelData;
    private String replyToId;

}
