package com.github.crypto.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EthereumPriceMarketEventProcessor extends AbstractMarketEventProcessor {

    @Value("${feed.eth-price-market.id}")
    private String marketId;

    @Override
    public String marketId() {
        return marketId;
    }
}
