package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

    public Item getFabricByDomain(URL url) throws IOException {
        var host = DtoUtils.hostWithoutUrl(url);
        System.out.println("host getF " + host);

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
