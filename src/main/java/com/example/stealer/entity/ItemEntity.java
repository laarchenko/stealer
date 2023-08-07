package com.example.stealer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<PriceEntity> prices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private SiteEntity site;

    @ManyToMany(mappedBy = "items")
    List<UserEntity> users;

    String url;

    public void addPrice(PriceEntity price) {
        prices.add(price);
        price.setItem(this);
    }
}
