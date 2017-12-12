package com.github.crypto.processor;

import com.github.crypto.model.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinMarketEventProcessor extends AbstractMarketEventProcessor {

    @Value("${feed.coin-market.id}")
    private String marketId;
    @Value("#{'${feed.coin-market.acceptedCurrencies}'.split(',')}")
    private List<String> acceptedCurrencies;

    @Override
    public String marketId() {
        return marketId;
    }

    @Override
    public Boolean processEvent(Currency event) {
        return acceptedCurrencies.contains(event.id);
    }
}
