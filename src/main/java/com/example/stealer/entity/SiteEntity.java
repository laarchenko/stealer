package com.example.stealer.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "site")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Boolean enabled;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<ItemEntity> items;

}
