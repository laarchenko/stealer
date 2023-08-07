package com.example.stealer.entity;

import com.example.stealer.enums.SiteName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;

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

    SiteName siteName;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<ItemEntity> items;

}
