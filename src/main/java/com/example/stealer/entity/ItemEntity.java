package com.example.stealer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.List;

@Data
@Entity
@Table(name = "items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @HashCodeExclude
    @ToString.Exclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ItemDetailsEntity> itemDetails;

    @HashCodeExclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false)
    SiteEntity site;

    @Column(name = "url", nullable = false)
    String url;

    @HashCodeExclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<SubscriptionEntity> subscriptions;
}
