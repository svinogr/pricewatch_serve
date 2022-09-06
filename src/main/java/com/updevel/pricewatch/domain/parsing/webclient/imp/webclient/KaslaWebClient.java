package com.updevel.pricewatch.domain.parsing.webclient.imp.webclient;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.updevel.pricewatch.domain.parsing.webclient.WebClientable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KaslaWebClient implements WebClientable {
    @Override
    public WebClient getWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.getDefault());
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        // webClient.waitForBackgroundJavaScript(5000);


        return webClient;
    }
}
