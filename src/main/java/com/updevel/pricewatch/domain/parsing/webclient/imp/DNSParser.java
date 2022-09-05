package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;

import java.io.IOException;

public class DNSParser implements Parserable {
    private WebClientable webClientable;

    public DNSParser(WebClientable webClientable) {
        this.webClientable = webClientable;
    }

    @Override
    public Item getParsedItem(String url) throws IOException {
        WebClient webCliet = webClientable.getWebClient();
        Page page = webCliet.getPage(url + "window.location.hash");
        System.out.println(webCliet);
        System.out.println("****" + page.getWebResponse().getContentType());
        WebResponse webResponse = page.getWebResponse();
        if (webResponse.getContentType() == "application/json") {
            String jsonString = webResponse.getContentAsString();
            System.out.println(jsonString);
            System.out.println(webCliet);
        }

        System.out.println();

        return null;
    }
}
