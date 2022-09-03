package com.updevel.pricewatch.domain.parsing.webclient;

public enum Host {
    DNS("dns-shop.ru "),
    KASLA("kasla.ru"),
    POLUS("polus.ru"),
    CITILINK("citilink.ru");
    private String host;

    Host(String s) {
        this.host = s;
    }

    public String getHost() {
        return host;
    }
}
