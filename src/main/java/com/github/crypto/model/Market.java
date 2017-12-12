package com.github.crypto.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("prototype")
public class Market {
    public String id;
    private Map<String, Currency> currencies = new ConcurrentHashMap<>();

    public void setId(String id) {
        this.id = id;
    }

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
