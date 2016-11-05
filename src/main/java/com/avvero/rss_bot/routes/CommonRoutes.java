package com.avvero.rss_bot.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Avvero
 */
@Component
public class CommonRoutes extends RouteBuilder {

    @Autowired
    private JacksonDataFormat jsonFormat;

    @Override
    public void configure() throws Exception {
        from("activemq:rss")
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .unmarshal(jsonFormat)
                .to("bean:rssConsumer?method=receive");
    }

}
