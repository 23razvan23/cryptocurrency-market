package com.github.crypto.notification;

import com.github.crypto.model.Currency;

public interface NotificationService {
    void onUpdate(Currency currency);
}
