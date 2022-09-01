package com.updevel.pricewatch.domain;

import com.updevel.pricewatch.db.entities.ItemEntity;
import com.updevel.pricewatch.db.repo.ItemRepo;
import com.updevel.pricewatch.db.repo.PriceRepo;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServiceItem implements ItemServiceInterface {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private PriceRepo priceRepo;

    @Override
    public List<Item> getAll() {
        return DtoUtils.listEntityToItem(itemRepo.findAll());
    }

    @Override
    public Item getById(Long id) {
        return null;
    }

    @Override
    public List<Item> getByList(List<Long> list) {
        return null;
    }

    @Override
    public Item addToDb(Item item) {
        ItemEntity itemEntity = DtoUtils.itemToEntity(item);
        ItemEntity save = null;
        var duplicate = itemRepo.findDuplicate(item.getUrl_link());
        // какая интересная фишка
        save = Objects.requireNonNullElseGet(duplicate, () -> itemRepo.save(itemEntity));

        return DtoUtils.entityToItem(save);
    }
}
