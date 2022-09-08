package com.updevel.pricewatch.db.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url_link", unique = true)
    private String urlLink;

    @Column(name = "img_link")
    private String imgLink;

    @Column(name = "host")
    private String host;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL)
    private List<PriceEntity> list;

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

    public List<PriceEntity> getList() {
        return list;
    }

    public void setList(List<PriceEntity> list) {
        this.list = list;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public PriceEntity getLastPriceOrNull() {
        if (list.isEmpty()) return null;

        return list.get(list.size() - 1);
    }
}
