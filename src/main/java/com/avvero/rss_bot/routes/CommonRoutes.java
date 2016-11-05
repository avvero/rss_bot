package com.avvero.rss_bot.routes;

import com.avvero.rss_bot.domain.Event;
import com.avvero.rss_bot.entity.bf.ChannelAccount;
import com.avvero.rss_bot.entity.bf.ConversationAccount;
import com.avvero.rss_bot.entity.bf.ConversationMessage;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Override
    public void configure() throws Exception {
        from("activemq:rss")
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .unmarshal().json(JsonLibrary.Jackson, Event.class)
                .to("bean:commonRoutes?method=map")
                .to("bean:botFrameworkService?method=send");
    }

    public ConversationMessage map(Event event) {
        ConversationMessage echo = new ConversationMessage();
        echo.setChannelId("skype");
        echo.setConversation(new ConversationAccount(null, conversations.get(0), null));
        echo.setType("message");
        echo.setText(event.getItem().getDescription());
        return echo;
    }

}
