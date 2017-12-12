package com.github.crypto.feed.parser;

import com.github.crypto.model.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class EthereumPriceMarketParser {

    public Stream<Currency> retrieve(String marketResponse) {
        Element body = Jsoup.parse(marketResponse).body();
        Elements metricsElement = body.select("div.metrics");

        Currency currency = null;
        try {
            currency = mapToCurrency(metricsElement);
            currency.id = "id-" + body.select("div.hero-banner").attr("data-name").toLowerCase();
        } catch (Exception e) {
            //todo handling
            e.printStackTrace();
        }
        return Stream.of(currency);
    }

    private Currency mapToCurrency(Elements elements) {
        Currency currency = new Currency();
        currency.price = Double.valueOf(elements
                .select("div.metrics__primary > span.metrics__primary__value > span#ep-price")
                .text()
                .replace(",", ""));
        Elements secondaryMetricsElements = elements.select("div.metrics__secondary > div.metric-item");
        currency.change24h = secondaryMetricsElements.get(0).select("span.metric-item__value").text();
        currency.volume24h = secondaryMetricsElements.get(3).select("span.metric-item__value").text();
        currency.marketCap = secondaryMetricsElements.get(4).select("span.metric-item__value").text();
        return currency;
    }
}
