package com.github.crypto.controller;

import com.github.crypto.model.Currency;
import com.github.crypto.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MarketController {
    private static final String DEFAULT_CURRENCY_ID = "id-ethereum";
    private static final double DEFAULT_CURRENCY_PRICE = 0.0;

    @Autowired
    private Market market;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Double getDefaultCurrencyPrice() {
        return retrievePrice(DEFAULT_CURRENCY_ID);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Double getCurrencyPrice(@PathVariable String id) {
        return retrievePrice(id);
    }

    private Double retrievePrice(String currencyId) {
        return Optional.ofNullable(market.getCurrency(currencyId))
                .map(Currency::getPrice)
                .orElse(DEFAULT_CURRENCY_PRICE);
    }
}
