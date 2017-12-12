package com.github.crypto.aggregator;

import com.github.crypto.model.Currency;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MarketAggregator {

    //<currency_id, <market_id, currency_price>>
    private Map<String, Map<String, Double>> currenciesMap = new HashMap<>();

    public void onUpdate(Currency currency, String marketId) {
        currenciesMap.computeIfAbsent(currency.id, v -> new HashMap<>());

        currenciesMap.get(currency.id)
                .put(marketId, currency.price);
    }

    public Double getAvgPrice(String currencyId) {
        Map<String, Double> marketValues = currenciesMap.get(currencyId);
        if (marketValues == null) {
            return null;
        }

        return marketValues.entrySet().stream()
                .mapToDouble(Map.Entry::getValue).sum()
                /
                marketValues.size();
    }
}
