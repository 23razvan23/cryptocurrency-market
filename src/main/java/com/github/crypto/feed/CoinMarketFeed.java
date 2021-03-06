package com.github.crypto.feed;

import com.github.crypto.feed.parser.CoinMarketParser;
import com.github.crypto.processor.CoinMarketEventProcessor;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class CoinMarketFeed {

    @Autowired
    private CoinMarketParser parser;
    @Autowired
    private CoinMarketEventProcessor processor;
    @Value("${feed.coin-market.url}")
    private String url;

    @PostConstruct
    public void start() {
        Runnable runnable = () -> {
            String response = queryMarket();
            if (response != null) {
                parser.retrieve(response)
                        .forEach(processor::onEvent);
            }
        };

        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(runnable, 0, 30,
                TimeUnit.SECONDS);
    }

    private String queryMarket() {
        try {
            return Unirest.get(url)
                    .asString()
                    .getBody();
        } catch (UnirestException e) {
            //todo logging
            System.out.println(e.getMessage());
            return null;
        }
    }
}
