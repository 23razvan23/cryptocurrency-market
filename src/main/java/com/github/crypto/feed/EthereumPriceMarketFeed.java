package com.github.crypto.feed;

import com.github.crypto.feed.parser.EthereumPriceMarketParser;
import com.github.crypto.processor.EthereumPriceMarketEventProcessor;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Component
public class EthereumPriceMarketFeed {

    @Autowired
    private EthereumPriceMarketParser parser;
    @Autowired
    private EthereumPriceMarketEventProcessor processor;
    //on this market we have multiple urls (one for each currency)
    @Value("#{'${feed.eth-price-market.url}'.split(',')}")
    private List<String> urls;

    @PostConstruct
    public void start() {
        Runnable runnable = () -> Stream.of(urls.toArray(new String[urls.size()]))
                .map(this::queryMarket)
                .filter(Objects::nonNull)
                .flatMap(parser::retrieve)
                .filter(Objects::nonNull)
                .forEach(processor::onEvent);

        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(runnable, 0, 30,
                TimeUnit.SECONDS);
    }

    private String queryMarket(String url) {
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
