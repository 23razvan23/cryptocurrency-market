package com.github.crypto.feed.parser;

import com.github.crypto.model.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoinMarketParser implements MarketParser {

    public List<Currency> retrieve(String html) {
        return Jsoup.parse(html)
                .select("table#currencies > tbody > tr")
                .stream()
                .map(this::mapToCurrency)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Currency mapToCurrency(Element element) {
        Currency currency = new Currency();
        currency.id = element.id();
        currency.name = element.select("td.no-wrap.currency-name > a").text();
        currency.marketCap = element.select("td.no-wrap.market-cap.text-right").text();
        String priceText = element.select("td.no-wrap.text-right > a.price").text();
        currency.price = Double.valueOf(priceText.replace("$", ""));
        currency.volume24h = element.select("td.no-wrap.text-right > a.volume").text();
        currency.change24h = element.select("td.no-wrap.percent-24h.text-right.positive_change").text();
        return currency;
    }
}
