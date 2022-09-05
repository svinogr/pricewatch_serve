package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.parsing.webclient.ParseFabricable;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;

public class ParserFabric implements ParseFabricable {
    @Override
    public Parserable getFabricByDomain(String host) {
        Parserable parserable = null;
        switch (host) {
            case "www.dns-shop.ru" -> {
                WebClientable w = new DNSWebClient();
                parserable = new DNSParser(w);
                break;
            }
            case "www.kasla.ru" -> {
                ///WebClientable w = new KaslaWebClient();
                //TODO
                parserable = new KaslaParser();
                break;
            }
            case "www.polus.ru" -> {
                WebClientable w = new PolusWebClient();
                parserable = new PolusParser(w);
                break;
            }
            case "www.citilink.ru" -> {
                WebClientable w = new CitilinkWebClient();
                parserable = new CitylinkParser(w);
                break;
            }
        }

        return parserable;
    }
}
