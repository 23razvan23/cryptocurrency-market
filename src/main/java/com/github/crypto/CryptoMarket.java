package com.github.crypto;

import com.github.crypto.model.Currency;

import java.util.List;

public interface CryptoMarket {
    List<Currency> retrieve(String marketResponse);
}
