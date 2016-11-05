package com.avvero.rss_bot.config;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Avvero
 */
@Configuration
public class JacksonConfiguration {
    @Bean
    public JSR310Module jsr310Module() {
        return new JSR310Module();
    }
}