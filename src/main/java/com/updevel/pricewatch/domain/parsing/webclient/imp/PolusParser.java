package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.imp.webclient.PolusWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class PolusParser implements Parserable {
    @Autowired
    private PolusWebClient polusWebClient;

    @Override
    public Item getParsedItem(URL url) {
        return null;
    }
}
