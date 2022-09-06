package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class ParsGetway {
    @Autowired
    private KaslaParser kaslaParser;
    @Autowired
    private DNSParser dnsParser;
    @Autowired
    private PolusParser polusParser;
    @Autowired
    private CitylinkParser citylinkParser;

    public Item getFabricByDomain(String url) throws IOException {
        var host = new URL(url).getHost();
        switch (host) {
            case "www.dns-shop.ru" -> {
                return dnsParser.getParsedItem(url);
            }
            case "www.kasla.ru" -> {
                return kaslaParser.getParsedItem(url);
            }
            case "www.polus.ru" -> {
                return polusParser.getParsedItem(url);
            }
            case "www.citilink.ru" -> {
                return citylinkParser.getParsedItem(url);
            }
            default -> {
                throw new MalformedURLException();
            }
        }

    }
}
