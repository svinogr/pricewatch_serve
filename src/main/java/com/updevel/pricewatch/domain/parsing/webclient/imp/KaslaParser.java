package com.updevel.pricewatch.domain.parsing.webclient.imp;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.model.Price;
import com.updevel.pricewatch.domain.parsing.webclient.Parserable;
import com.updevel.pricewatch.domain.parsing.webclient.imp.webclient.KaslaWebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Component
public class KaslaParser implements Parserable {
    @Autowired
    private KaslaWebClient kaslaWebClient;

    @Override
    public Item getParsedItem(URL url) throws IOException {
        WebClient webCliet = kaslaWebClient.getWebClient();
        HtmlPage page = webCliet.getPage(url);
        String s = page.asXml();
        Document parse = Jsoup.parse(s);
        Element section = parse.getElementById("tableContainer");
        String img = section.getElementById("pictureContainer").getElementsByTag("a").attr("href").toString();
        String title = parse.getElementById("right").getElementsByTag("h1").html();
        String[] split = section.getElementById("elementTools").getElementsByClass("priceVal").get(0).text().split("руб");
        String priceS = split[0].trim().replace(" ", "");

        Price price = new Price();
        price.setPrice(Double.parseDouble(priceS));
        price.setDate(new Date().getTime());

        Item item = new Item();
        item.setTitle(title);
        item.setImgLink(img);
        item.getPriceList().add(price);

        prepareUrl(item, url);

        return item;
    }
}
