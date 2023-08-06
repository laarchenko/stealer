package com.example.stealer.entity;

import com.example.stealer.entity.jsonb.SizeInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
