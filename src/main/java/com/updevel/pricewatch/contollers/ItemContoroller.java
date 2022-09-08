package com.updevel.pricewatch.contollers;

import com.updevel.pricewatch.domain.ItemServiceInterface;
import com.updevel.pricewatch.domain.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemContoroller {
    @Autowired
    private ItemServiceInterface itemService;

    @GetMapping("/all")
    @ResponseBody
    public List<Item> getAllItems() {
        return itemService.getAll();
    }

    @PostMapping("/url/")
    @ResponseBody
    public Item addNewItemByUrl(@RequestBody Item item, HttpServletResponse response) {
        try {
            item = itemService.addToDbByUrl(item.getUrlLink());

        } catch (MalformedURLException e) {
            response.setStatus(400);
            return item;
        } catch (IOException e) {
            response.setStatus(500);
            return item;
        }

        return item;
    }

    @GetMapping("")
    @ResponseBody
    public List<Item> getItemsById(@RequestBody List<Item> list, HttpServletResponse response) {
        try {
            list = itemService.getByList(list);

        } catch (EntityNotFoundException e) {
            response.setStatus(404);
        }

        return list;
    }

}
