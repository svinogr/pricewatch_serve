package com.updevel.pricewatch.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private long id;
    private String title;
    private String urlLink;
    private String imgLink;
    private List<Price> priceList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urlLink='" + urlLink + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", priceList=" + priceList.size() +
                '}';
    }

    public Price getLastPriceOrNull() {
        if (priceList.isEmpty()) return null;

        return priceList.get(priceList.size() - 1);
    }
}
