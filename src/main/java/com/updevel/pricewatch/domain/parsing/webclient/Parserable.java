package com.updevel.pricewatch.domain.parsing.webclient;

import com.updevel.pricewatch.domain.model.Item;

import java.io.IOException;

public interface Parserable {
    Item getParsedItem(String url) throws IOException;
}
