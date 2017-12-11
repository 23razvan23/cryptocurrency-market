package com.github.crypto.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Market {
    private Map<String, Currency> currencies = new ConcurrentHashMap<>();

    //retrieval operations do not block
    //(need to decide if ConcurrentHashMap is needed - currently only one thread "put" entries)
    public Currency getCurrency(String id) {
        return currencies.get(id);
    }

    public boolean tryUpdate(Currency current) {
        Currency previous = currencies.put(current.id, current);
        return !current.equals(previous);
    }
}
