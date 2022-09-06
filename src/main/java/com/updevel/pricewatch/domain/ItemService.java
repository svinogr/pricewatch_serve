package com.updevel.pricewatch.domain;

import com.updevel.pricewatch.db.entities.HostEntity;
import com.updevel.pricewatch.db.entities.ItemEntity;
import com.updevel.pricewatch.db.entities.PriceEntity;
import com.updevel.pricewatch.db.repo.HostRepo;
import com.updevel.pricewatch.db.repo.ItemRepo;
import com.updevel.pricewatch.domain.model.Item;
import com.updevel.pricewatch.domain.model.Price;
import com.updevel.pricewatch.domain.parsing.webclient.imp.ParsGetway;
import com.updevel.pricewatch.domain.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ItemService implements ItemServiceInterface {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private HostRepo hostRepo;

    @Autowired
    private ParsGetway pars;

    @Override
    public List<Item> getAll() {
        return DtoUtils.listEntityToItem(itemRepo.findAll());
    }

    @Override
    public Item getById(Long id) throws EntityNotFoundException {
        ItemEntity referenceById = itemRepo.getReferenceById(id);

        System.out.println(referenceById);

        var lastDate = new Date(referenceById.getList().get(referenceById.getList().size() - 1).getDate());
        var nowDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        System.out.println(calendar.before(new Date()));

        if (lastDate.getDay() < nowDate.getDay()) {
            //TODO запрос на актуалку цены
            System.out.println("actualize price......." + " old date " + lastDate + "- " + nowDate);
            System.out.println("actualize price......." + " old date " + lastDate.getTime() + "- " + nowDate.getTime());
        }

        return DtoUtils.entityToItem(referenceById);
    }

    @Override
    public List<Item> getByList(List<Long> list) {
        return null;
    }

    @Override
    public Item addToDb(Item item) {
        ItemEntity itemEntity = DtoUtils.itemToEntity(item);
        ItemEntity save = null;
        var duplicate = itemRepo.findDuplicate(item.getUrlLink());
        // какая интересная фишка
        save = Objects.requireNonNullElseGet(duplicate, () -> itemRepo.save(itemEntity));

        return DtoUtils.entityToItem(save);
    }

    @Override
    public Item addToDbByUrl(String url) throws IOException {
        Item result = null;

        var host = new URL(url).getHost();
        HostEntity hostEntity = hostRepo.findByHost(host);
        if (hostEntity == null) throw new MalformedURLException();

        var duplicate = itemRepo.findDuplicate(url);

        // если дубликата нет то нужно распарсить. записать в базу и отдать записаное
        // если есть проверить последнюю дату.
        // 1 если дата старая распарсить новую дату записать в базу и отдать
        // 2 инчае отдать

        if (duplicate == null) {
            //TODO call service parsing
            System.out.println("call service parsing");
            System.out.println("новая запись в базу");
            System.out.println(host);
            Item parsed = pars.getFabricByDomain(url);
            ItemEntity itemEntity = itemRepo.save(DtoUtils.itemToEntity(parsed));

            result = DtoUtils.entityToItem(itemEntity);
        } else {
            // узкое место. вдруг нолт вместо цены. но не должно быть нуля именно здесь )
            if (checkLastDateIsOldest(duplicate.getLastPriceOrNull().getDate())) {
                Item parsed = pars.getFabricByDomain(url);
                Price price = parsed.getPriceList().get(0);

                PriceEntity priceEntity = new PriceEntity();
                priceEntity.setPrice(price.getPrice());
                priceEntity.setDate(price.getDate());

                duplicate.getList().add(priceEntity);
                ItemEntity save = itemRepo.save(duplicate);

                System.out.println("call service parsing");
                System.out.println("новая цена добавлена");

                result = DtoUtils.entityToItem(save);

            } else {
                System.out.println("dublicat");
                result = DtoUtils.entityToItem(duplicate);
            }
        }

        return result;
    }

    private boolean checkLastDateIsOldest(long lastDate) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(new Date(lastDate));
        boolean before = calendar.before(calendar2.getTime());
        System.out.println(calendar.getTime());
        System.out.println(calendar2.getTime());
        System.out.println(before);

        return before;
    }
}
