package com.github.crypto.notification;

import com.github.crypto.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class SnapshotNotificationService implements NotificationService {

    @Override
    public void onUpdate(Currency currency) {
        //todo process notification rules

    }
}
