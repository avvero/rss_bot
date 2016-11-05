package com.avvero.rss_bot.service;

import com.avvero.rss_bot.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Avvero
 */
@Slf4j
@Service
public class RssConsumer {

    public void receive(Event event) {
        log.info(" " + event);
    }

}
