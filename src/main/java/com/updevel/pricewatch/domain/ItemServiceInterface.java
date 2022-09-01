package com.updevel.pricewatch.domain;

import com.updevel.pricewatch.domain.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    List<Item> getAll();
    Item getById(Long id);
    List<Item> getByList(List<Long> list);

    Item addToDb(Item item);
}
