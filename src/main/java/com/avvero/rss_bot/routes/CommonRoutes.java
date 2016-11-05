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
        from("direct:push-event")
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .marshal(jsonFormat)
                .log("${body}")
                .to("activemq:rss");

        from("activemq:rss")
                .setHeader("Content-Type", constant("application/json; charset=utf-8"))
                .unmarshal(jsonFormat)
                .to("bean:events-handle?method=processEvent");
    }

}
