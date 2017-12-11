package com.github.crypto.snapshot;

public interface EventProcessor<T> {
    void onEvent(T event);
}
