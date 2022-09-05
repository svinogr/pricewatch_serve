package com.updevel.pricewatch.contollers;

import com.updevel.pricewatch.domain.ItemServiceInterface;
import com.updevel.pricewatch.domain.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
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

    @PostMapping("/url/")
    @ResponseBody
    public Item addNewItemByUrl(@RequestBody Item url, HttpServletResponse response) {

      try {
          url = serviceItem.addToDbByUrl(url.getUrlLink());

      }catch (MalformedURLException e){
          response.setStatus(400);
          return new Item();
      }catch ( IOException e) {
          response.setStatus(500);
          return new Item();
      }

      return url;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Item getItemById(@PathVariable long id) {
        return serviceItem.getById(id);
    }
}
