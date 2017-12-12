package com.github.crypto.processor;

import com.github.crypto.aggregator.MarketAggregator;
import com.github.crypto.model.Market;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMarketEventProcessor implements MarketEventProcessor {

    @Autowired
    private Market market;
    @Autowired
    private MarketAggregator marketAggregator;

    @Override
    public Market market() {
        return market;
    }

    @Override
    public MarketAggregator marketAggregator() {
        return marketAggregator;
    }
}
