package com.updevel.pricewatch.domain.util;

import com.updevel.pricewatch.db.entities.ItemEntity;
import com.updevel.pricewatch.db.entities.PriceEntity;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.model.Price;

import java.util.ArrayList;
import java.util.List;

public class DtoUtils {
    public static Item entityToItem(ItemEntity entity) {
        var i = new Item();
        i.setId(entity.getId());
        i.setTitle(entity.getTitle());
        i.setUrlLink(entity.getUrlLink());
        i.setImgLink(entity.getImgLink());
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
        itemEntity.setTitle(item.getTitle());
        itemEntity.setList(new ArrayList<>());

        var priceEntity = new PriceEntity();
        priceEntity.setItemId(itemEntity);

        priceEntity.setPrice(item.getPriceList().get(0).getPrice());
        priceEntity.setDate(item.getPriceList().get(0).getDate());

        itemEntity.getList().add(priceEntity);

        return itemEntity;
    }
}
