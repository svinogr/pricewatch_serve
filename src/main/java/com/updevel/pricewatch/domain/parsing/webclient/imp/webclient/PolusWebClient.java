package com.updevel.pricewatch.domain.parsing.webclient.imp.webclient;

import com.gargoylesoftware.htmlunit.WebClient;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;
import org.springframework.stereotype.Component;

@Component
public class PolusWebClient implements WebClientable {
    @Override
    public WebClient getWebClient() {
        return null;
    }
}
