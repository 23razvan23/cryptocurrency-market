package com.github.crypto.feed.parser;

import com.github.crypto.model.Currency;

import java.util.List;

public interface MarketParser {
    List<Currency> retrieve(String marketResponse);
}
