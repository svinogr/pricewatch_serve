package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.parsing.webclient.Host;
import com.updevel.pricewatch.domain.parsing.webclient.ParseFabricable;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;

public class ParserFabric implements ParseFabricable {
    @Override
    public Parserable getFabricByDomain(Host host) {
        Parserable parserable = null;
        switch (host) {
            case DNS -> {
                WebClientable w = new DNSWebClient();
                parserable = new DNSParser(w);
                break;
            }
            case KASLA -> {
                WebClientable w = new KaslaWebClient();
                parserable = new KaslaParser(w);
                break;
            }
            case POLUS -> {
                WebClientable w = new PolusWebClient();
                parserable = new PolusParser(w);
                break;
            }
            case CITILINK -> {
                WebClientable w = new CitilinkWebClient();
                parserable = new CitylinkParser(w);
                break;
            }
        }

        return parserable;
    }
}
