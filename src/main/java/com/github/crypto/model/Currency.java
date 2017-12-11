package com.github.crypto.model;

public class Currency {
    public String id;
    public String name;
    public String marketCap;
    public Double price;
    public String volume24h;
    public String circulatingSupply;
    public String change24h;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(String volume24h) {
        this.volume24h = volume24h;
    }

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public String getChange24h() {
        return change24h;
    }

    public void setChange24h(String change24h) {
        this.change24h = change24h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (marketCap != null ? !marketCap.equals(currency.marketCap) : currency.marketCap != null) return false;
        return price != null ? price.equals(currency.price) : currency.price == null;
    }

    @Override
    public int hashCode() {
        int result = marketCap != null ? marketCap.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
