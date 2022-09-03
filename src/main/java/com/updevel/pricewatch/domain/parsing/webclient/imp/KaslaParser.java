package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;

public class KaslaParser implements Parserable {
    private WebClientable w;

    public KaslaParser(WebClientable w) {
        this.w = w;
    }

    @Override
    public Item getParsedItem(String url) {
        return null;
    }
}
