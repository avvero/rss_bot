package com.avvero.rss_bot.routes;

import com.avvero.rss_bot.dto.bf.*;
import com.avvero.rss_bot.dto.data.Account;
import com.avvero.rss_bot.dto.data.Message;
import com.avvero.rss_bot.dto.rss.Event;
import com.avvero.rss_bot.service.Parser;
import com.avvero.rss_bot.service.UrlShortener;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
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
    @Value("${rss_bot.queue_name_rss}")
    private String rssQueueName;
    @Value("${rss_bot.queue_name_conversation}")
    private String conversationQueueName;
    @Value("${rss_bot.view}")
    private String view;


    @Override
    public void configure() throws Exception {
        from("activemq:" + rssQueueName)
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .unmarshal().json(JsonLibrary.Jackson, Event.class)
                .to("bean:commonRoutes?method=map")
                .to("bean:botFrameworkService?method=send");

        from("direct:push-conversation-message")
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .to("bean:commonRoutes?method=map")
                .marshal(jsonFormat)
                .to("activemq:" + conversationQueueName);
    }

    public ConversationMessage map(Event event) throws UnsupportedEncodingException {
        ConversationMessage echo = new ConversationMessage();
        echo.setChannelId("skype");
        echo.setConversation(new ConversationAccount(null, conversations.get(0), null));
        if ("card".equals(view)) {
            echo.setType("message/card.carousel");
            Attachment attachment = new Attachment();
            echo.setAttachments(Arrays.asList(attachment));

            attachment.setContentType("application/vnd.microsoft.card.hero");
            AttachmentEntry attachmentEntry = new AttachmentEntry();
            attachmentEntry.setTitle(event.getItem().getTitle());
            attachmentEntry.setText(parser.html2text(event.getItem().getDescription()));
            attachmentEntry.setButtons(Arrays.asList(new Button("openUrl", "На сайт", urlShortener.make(event.getItem().getLink()))));
            attachment.setContent(attachmentEntry);
        } else {
            echo.setType("message");
            echo.setText(event.getItem().getTitle() + " <a href='" + urlShortener.make(event.getItem().getLink()) + "'>На сайт</a>");
        }
        return echo;
    }

    public Message map (ConversationMessage conversationMessage) {
        String text = parser.html2text(conversationMessage.getText(), Whitelist.none());
        //Цитирование, нужная правая часть
        String quotes = "&lt;&lt;&lt;";
        if (text.contains(quotes)) {
            text = text.substring(text.indexOf(quotes) + quotes.length() + 1, text.length());
        }
        return new Message(
                conversationMessage.getId(),
                text,
                conversationMessage.getTimestamp(),
                conversationMessage.getChannelId(),
                conversationMessage.getConversation().getId(),
                new Account(conversationMessage.getFrom().getId(), conversationMessage.getFrom().getName()),
                new Account(conversationMessage.getRecipient().getId(), conversationMessage.getRecipient().getName()));
    }

}
