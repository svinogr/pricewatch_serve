package com.updevel.pricewatch.domain.parsing.webclient;

import com.updevel.pricewatch.domain.model.Item;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public interface Parserable {
    Item getParsedItem(URL fullUrlWithProtocol) throws IOException;

    default void prepareUrl(Item item, URL url) {
            String host = url.getHost();
            String protocol = url.getProtocol();
            String path =  url.getPath();

            if(!host.contains("www")) {
                host = "www." + host;
            }

            item.setHost(protocol +"://" + host);
            item.setUrlLink(path);
            System.out.println(item.getHost() + "-- " + path);
    }
}
