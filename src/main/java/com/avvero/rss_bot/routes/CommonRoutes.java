package com.avvero.rss_bot.routes;

import com.avvero.rss_bot.domain.Event;
import com.avvero.rss_bot.entity.bf.*;
import com.avvero.rss_bot.service.Parser;
import com.avvero.rss_bot.service.UrlShortener;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author Avvero
 */
@Component
public class CommonRoutes extends RouteBuilder {

    @Autowired
    private JacksonDataFormat jsonFormat;
    @Value("#{'${rss_bot.conversations}'.split(',')}")
    private List<String> conversations;
    @Autowired
    private Parser parser;
    @Autowired
    private UrlShortener urlShortener;
    @Value("${rss_bot.queue_name}")
    private String queueName;


    @Override
    public void configure() throws Exception {
        from("activemq:" + queueName)
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .unmarshal().json(JsonLibrary.Jackson, Event.class)
                .to("bean:commonRoutes?method=map")
                .to("bean:botFrameworkService?method=send");
    }

    public ConversationMessage map(Event event) throws UnsupportedEncodingException {
        ConversationMessage echo = new ConversationMessage();
        echo.setChannelId("skype");
        echo.setConversation(new ConversationAccount(null, conversations.get(0), null));
        echo.setType("message/card.carousel");
        Attachment attachment = new Attachment();
        echo.setAttachments(Arrays.asList(attachment));

        attachment.setContentType("application/vnd.microsoft.card.hero");
        AttachmentEntry attachmentEntry = new AttachmentEntry();
        attachmentEntry.setTitle(event.getItem().getTitle());
        attachmentEntry.setText(parser.html2text(event.getItem().getDescription()));
        attachmentEntry.setButtons(Arrays.asList(new Button("openUrl", "На сайт", urlShortener.make(event.getItem().getLink()))));
//        attachmentEntry.setImages(Arrays.asList(new Image(event.getItem().)));
        attachment.setContent(attachmentEntry);

        return echo;
    }

}
