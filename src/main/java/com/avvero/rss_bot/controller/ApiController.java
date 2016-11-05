package com.avvero.rss_bot.controller;

import com.avvero.rss_bot.entity.bf.ConversationMessage;
import com.avvero.rss_bot.service.BotFrameworkService;
import com.avvero.rss_bot.service.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.messaging.support.MessageBuilder.withPayload;

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
    @Qualifier("sendMessageFlowChannel")
    public MessageChannel sendMessageFlowChannel;
    @Autowired
    @Qualifier("hubIncomingFlow.input")
    public MessageChannel hubIncomingChannel;
    @Autowired
    CommandService commandService;

    @RequestMapping(value = "/endpoint", method = RequestMethod.POST)
    public void endpoint(@RequestBody ConversationMessage message) {
        log.info("ENDPOINT START");
        log.info(message.toString());

        if ("message".equals(message.getType())) {
            ConversationMessage response = commandService.process(message);
            sendMessageFlowChannel.send(withPayload(response).build());
        } else if ("conversationUpdate".equals(message.getType())) {
            ConversationMessage echo = new ConversationMessage();
            echo.setChannelId(message.getChannelId());
            echo.setConversation(message.getConversation());
            echo.setFrom(message.getRecipient());
            echo.setRecipient(message.getFrom());
            echo.setType("message");
            echo.setText("<ss type=\"hi\">(wave)</ss>");
            sendMessageFlowChannel.send(withPayload(echo).build());
        }

        log.info("ENDPOINT END");
    }
}
