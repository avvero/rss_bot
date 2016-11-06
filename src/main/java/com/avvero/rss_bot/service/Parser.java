package com.avvero.rss_bot.service;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

/**
 * @author Avvero
 */
@Service
public class Parser {

    public String html2text(String html) {
        return Jsoup.clean(html, Whitelist
                        .simpleText()
//                        .addTags("a")
//                        .addAttributes("a", "href", "title")
        )
                .replace("&nbsp;", " ")
                .replace("\n", " ")
                .replace("  ", " ");
    }

}
