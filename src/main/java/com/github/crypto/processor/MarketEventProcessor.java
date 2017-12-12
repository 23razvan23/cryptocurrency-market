package com.github.crypto.processor;

import com.github.crypto.aggregator.MarketAggregator;
import com.github.crypto.model.Currency;
import com.github.crypto.model.Market;

import javax.annotation.PostConstruct;

public interface MarketEventProcessor {

    @PostConstruct
    default void setup() {
        market().setId(marketId());
    }

    default void onEvent(Currency event) {
        if (processEvent(event)) {
            if (market().tryUpdate(event)) {
                //todo persist model
                marketAggregator().onUpdate(event, market().id);
            }
        }
    }

    default Boolean processEvent(Currency event) {
        return true;
    }

    Market market();

    String marketId();

    MarketAggregator marketAggregator();
}
