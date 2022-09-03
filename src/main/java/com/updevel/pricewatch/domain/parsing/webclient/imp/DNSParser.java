package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;

public class DNSParser implements Parserable {
    private WebClientable webClientable;

    public DNSParser(WebClientable webClientable) {
        this.webClientable = webClientable;
    }

    @Override
    public Item getParsedItem(String url) {
        return null;
    }
}
