package com.avvero.rss_bot.controller;

import com.avvero.rss_bot.entity.bf.ConversationMessage;
import com.avvero.rss_bot.service.BotFrameworkService;
import com.avvero.rss_bot.service.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fxdev-belyaev-ay
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    BotFrameworkService botFrameworkService;
    @Autowired
    CommandService commandService;

    @RequestMapping(value = "/endpoint", method = RequestMethod.POST)
    public void endpoint(@RequestBody ConversationMessage message) {
        log.info("ENDPOINT START");
        log.info(message.toString());

        if ("message".equals(message.getType())) {
            ConversationMessage response = commandService.process(message);
            botFrameworkService.send(response);
        } else if ("conversationUpdate".equals(message.getType())) {
            ConversationMessage echo = new ConversationMessage();
            echo.setChannelId(message.getChannelId());
            echo.setConversation(message.getConversation());
            echo.setFrom(message.getRecipient());
            echo.setRecipient(message.getFrom());
            echo.setType("message");
            echo.setText("<ss type=\"hi\">(wave)</ss>");
            botFrameworkService.send(echo);
        }

        log.info("ENDPOINT END");
    }
}
