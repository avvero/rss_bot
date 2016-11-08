package com.avvero.rss_bot.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by fxdev-belyaev-ay on 03.11.16.
 */
public class LocalDateTimeStringDeserialize extends JsonDeserializer {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateAsString = p.getText();
        return LocalDateTime.parse(dateAsString, DateTimeFormatter.ISO_DATE_TIME);
    }
}
