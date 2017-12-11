package com.github.crypto.snapshot;

import com.github.crypto.model.Currency;
import com.github.crypto.model.Market;
import com.github.crypto.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinMarketEventProcessor implements EventProcessor<Currency> {

    @Autowired
    private Market market;
    @Autowired
    private NotificationService notificationService;

    @Override
    public void onEvent(Currency event) {
        boolean updated = market.tryUpdate(event);

        if (updated) {
            //todo persist model

            notificationService.onUpdate(event);
        }
    }
}
