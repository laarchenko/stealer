package com.example.stealer.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "sites")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Boolean enabled;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<Item> items;

    // TODO:

}
