package com.github.crypto.feed;

import com.github.crypto.snapshot.EventProcessor;

public interface Feed<T> {
    void start();

    void subscribe(EventProcessor<T> inspector);
}
