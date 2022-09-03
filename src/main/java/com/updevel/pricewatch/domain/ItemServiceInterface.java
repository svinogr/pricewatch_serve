package com.updevel.pricewatch.domain;

import com.updevel.pricewatch.domain.model.Item;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ItemServiceInterface {
    List<Item> getAll() throws EntityNotFoundException;
    Item getById(Long id) throws EntityNotFoundException;
    List<Item> getByList(List<Long> list) throws EntityNotFoundException;

    Item addToDb(Item item);

    Item addToDbByUrl(String url);
}
