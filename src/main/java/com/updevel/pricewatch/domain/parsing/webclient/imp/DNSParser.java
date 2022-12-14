package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.imp.webclient.DNSWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class DNSParser implements Parserable {
    @Autowired
    private DNSWebClient dnsWebClient;

    @Override
    public Item getParsedItem(URL url) throws IOException {
        WebClient webCliet = dnsWebClient.getWebClient();
        Page page = webCliet.getPage(url + "window.location.hash");
        System.out.println(webCliet);
        System.out.println("****" + page.getWebResponse().getContentType());
        WebResponse webResponse = page.getWebResponse();
        if (webResponse.getContentType() == "application/json") {
            String jsonString = webResponse.getContentAsString();
            System.out.println(jsonString);
            System.out.println(webCliet);
        }

        return null;
    }
}
