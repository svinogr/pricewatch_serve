package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;
import org.springframework.stereotype.Component;

@Component
public class DNSWebClient implements WebClientable {
    @Override
    public WebClient getWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.getDefault());
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        // webClient.waitForBackgroundJavaScript(5000);
        webClient.addRequestHeader("X-Requested-With", "XMLHttpRequest");
        webClient.addRequestHeader("Content-Type", "application/json");

        return webClient;
    }
}
