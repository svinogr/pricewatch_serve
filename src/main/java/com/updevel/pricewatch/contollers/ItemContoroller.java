package com.updevel.pricewatch.contollers;

import com.updevel.pricewatch.domain.ItemServiceInterface;
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

    /**
     * Item json:
     * {
     * "id": null,
     * "title": null,
     * "urlLink": null,
     * "imgLink": null,
     * "priceList": [
     * {
     * "id": null,
     * "itemId": null,
     * "price": null,
     * "date": null
     * }
     * ]
     * }
     *
     * @return
     */
    @PostMapping("/addJSon")
    @ResponseBody
    public Item addNewItem(@RequestBody Item item) {
        return serviceItem.addToDb(item);
    }

    @PostMapping("/url/{url}")
    @ResponseBody
    public Item addNewItemByUrl(@PathVariable String url) {
       return serviceItem.addToDbByUrl(url);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Item getItemById(@PathVariable long id) {
        return serviceItem.getById(id);
    }
}
