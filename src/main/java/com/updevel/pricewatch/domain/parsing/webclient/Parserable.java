package com.updevel.pricewatch.domain.parsing.webclient;

import com.updevel.pricewatch.domain.model.Item;

public interface Parserable {
    Item getParsedItem(String url);
}
