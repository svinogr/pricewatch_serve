package com.updevel.pricewatch.contollers;

import com.updevel.pricewatch.db.entities.ItemEntity;
import com.updevel.pricewatch.db.repo.ItemRepo;
import com.updevel.pricewatch.domain.ItemServiceInterface;
import com.updevel.pricewatch.domain.ServiceItem;
import com.updevel.pricewatch.domain.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemContoroller {
    @Autowired
    private ItemServiceInterface serviceItem;

    @GetMapping("/all")
    @ResponseBody
    public List<Item> getAllItems() {
        return serviceItem.getAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public Item addNewItem(@RequestBody Item item) {

        System.out.println("item");
        return serviceItem.addToDb(item);

    }
}
