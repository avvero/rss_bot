package com.avvero.rss_bot.service;

import com.avvero.rss_bot.entity.bf.ConversationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fxdev-belyaev-ay
 */
@Slf4j
@Service
public class CommandService {

    public ConversationMessage process(ConversationMessage incomingMessage) {
        ConversationMessage response = new ConversationMessage();
        response.setChannelId(incomingMessage.getChannelId());
        response.setConversation(incomingMessage.getConversation());
        response.setFrom(incomingMessage.getRecipient());
        response.setRecipient(incomingMessage.getFrom());
        response.setType("message");

        String command = incomingMessage.getText();
        command = command == null ? "" : command;
        try {
            response.setText("I am sorry, my responses are limited, you must ask the right questions.");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setText(e.getMessage());
        }
        return response;
    }

}
