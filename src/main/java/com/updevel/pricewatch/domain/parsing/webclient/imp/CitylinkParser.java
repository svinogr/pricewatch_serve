package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.imp.webclient.CitilinkWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CitylinkParser implements Parserable {
    @Autowired
    private CitilinkWebClient citilinkWebClient;

    @Override
    public Item getParsedItem(String url) {
        return null;
    }
}
