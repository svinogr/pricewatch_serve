package com.updevel.pricewatch.domain.util;

import com.updevel.pricewatch.db.entities.ItemEntity;
import com.updevel.pricewatch.db.entities.PriceEntity;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.model.Price;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DtoUtils {
    public static Item entityToItem(ItemEntity entity) {
        var i = new Item();
        i.setId(entity.getId());
        i.setTitle(entity.getTitle());
        i.setUrlLink(entity.getUrlLink());
        i.setImgLink(entity.getImgLink());
        i.setHost(entity.getHost());
        i.setPriceList(new ArrayList<>());

        entity.getList().forEach(priceEntity -> {
            var p = new Price();
            p.setId(priceEntity.getId());
            p.setItemId(priceEntity.getItemId().getId());
            p.setPrice(priceEntity.getPrice());
            p.setDate(priceEntity.getDate());

            i.getPriceList().add(p);
        });

        return i;
    }

    public static List<Item> listEntityToItem(List<ItemEntity> list) {
        var result = new ArrayList<Item>();

        list.forEach(itemEntity -> {
            var i = entityToItem(itemEntity);
            result.add(i);
        });

        return result;
    }

    public static ItemEntity itemToEntity(Item item) {
        System.out.println(item);
        var itemEntity = new ItemEntity();
        itemEntity.setUrlLink(item.getUrlLink());
        itemEntity.setImgLink(item.getImgLink());
        itemEntity.setHost(item.getHost());
        itemEntity.setTitle(item.getTitle());
        itemEntity.setList(new ArrayList<>());

        var priceEntity = new PriceEntity();
        priceEntity.setItemId(itemEntity);

        priceEntity.setPrice(item.getPriceList().get(0).getPrice());
        priceEntity.setDate(item.getPriceList().get(0).getDate());

        itemEntity.getList().add(priceEntity);

        return itemEntity;
    }

/*    public static String urlWithoutHost(URL url) {
        String result = null;

        try {
            String host = new url.getHost();
            String urls[] = url.split(host);

            result = urls[1];
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }*/

    public static String hostWithoutUrl(URL urlString) {
        String result = null;

            String host = urlString.getHost();

            if(!host.contains("www")) {
                host = "www." + host;
            }

            result = host;

        return result;
    }
}
