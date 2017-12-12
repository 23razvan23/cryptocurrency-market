package com.github.crypto.controller;

import com.github.crypto.aggregator.MarketAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MarketController {

    private static final double DEFAULT_CURRENCY_PRICE = 0.0;
    @Value("${currency.default-id}")
    private String defaultCurrencyId;
    @Autowired
    private MarketAggregator marketAggregator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Double getDefaultCurrencyPrice() {
        return retrievePrice(defaultCurrencyId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Double getCurrencyPrice(@PathVariable String id) {
        return retrievePrice(id);
    }

    private Double retrievePrice(String currencyId) {
        return Optional.ofNullable(marketAggregator.getAvgPrice(currencyId))
                .orElse(DEFAULT_CURRENCY_PRICE);
    }
}
