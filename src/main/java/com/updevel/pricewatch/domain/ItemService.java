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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Item getById(Long id) throws EntityNotFoundException, IOException {
        ItemEntity referenceById = itemRepo.getReferenceById(id);

        var lastDate = referenceById.getLastPriceOrNull().getDate();

        if (checkLastDateIsOldest(lastDate)) {
            referenceById = updateEntityWithNewPrice(referenceById);
        }

        return DtoUtils.entityToItem(referenceById);
    }

    @Override
    public List<Item> getByList(List<Item> list) {
        List<Long> collect = list.stream().map(Item::getId).collect(Collectors.toList());
        // return itemRepo.findAllById(collect).stream().map(entity -> DtoUtils.entityToItem(entity)).collect(Collectors.toList());
        List<Item> itemList =  itemRepo.findAllById(collect).stream().map(entity -> {
            if (checkLastDateIsOldest(entity.getLastPriceOrNull().getDate())) {
                try {
                    entity = updateEntityWithNewPrice(entity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return entity;
        }).map(DtoUtils::entityToItem).collect(Collectors.toList());

        if (itemList.isEmpty()) throw new EntityNotFoundException();

        return itemList;
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
    public Item addToDbByUrl(String urlString) throws MalformedURLException, IOException {
        Item result = null;
        URL url = new URL(urlString);
        boolean isHas = urlIsHostInDb(url);
        if (!isHas) throw new MalformedURLException();

        var duplicate = itemRepo.findDuplicate(url.getPath());

        // если дубликата нет то нужно распарсить. записать в базу и отдать записаное
        // если есть проверить последнюю дату.
        // 1 если дата старая распарсить новую дату записать в базу и отдать
        // 2 инчае отдать

        if (duplicate == null) {
            System.out.println("add new in db with parse");
            Item parsed = pars.getFabricByDomain(url);
            ItemEntity itemEntity = itemRepo.save(DtoUtils.itemToEntity(parsed));

            result = DtoUtils.entityToItem(itemEntity);
        } else {
            // узкое место. вдруг нолт вместо цены. но не должно быть нуля именно здесь )
            if (checkLastDateIsOldest(duplicate.getLastPriceOrNull().getDate())) {
                System.out.println("old price dublicat");
                ItemEntity save = updateEntityWithNewPrice(duplicate);
                result = DtoUtils.entityToItem(save);
            } else {
                System.out.println("dublicat");
                result = DtoUtils.entityToItem(duplicate);
            }
        }

        return result;
    }

    private boolean urlIsHostInDb(URL url) throws MalformedURLException {
        var host =  url.getHost();
        System.out.println(host);
        if (!host.contains("www")) {
            host = "www." + host;
            System.out.println(host);
        }

        HostEntity hostEntity = hostRepo.findByHost(host);

        return hostEntity != null;
    }

    private boolean checkLastDateIsOldest(long lastDate) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(new Date(lastDate));
        //LocalDate localDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
        LocalDate a = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate b = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));

       return a.isBefore(b);
    }

    private ItemEntity updateEntityWithNewPrice(ItemEntity oldEntity) throws IOException {
        Item parsed = pars.getFabricByDomain(new URL(oldEntity.getHost() + oldEntity.getUrlLink()));
        Price price = parsed.getPriceList().get(0);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setItemId(oldEntity);
        priceEntity.setPrice(price.getPrice());
        priceEntity.setDate(price.getDate());

        oldEntity.getList().add(priceEntity);

        return itemRepo.save(oldEntity);
    }
}
