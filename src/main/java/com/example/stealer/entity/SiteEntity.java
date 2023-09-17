package com.example.stealer.entity;

import com.example.stealer.enums.SiteName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.List;

@Entity
@Data
@Table(name = "site")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "display_name")
    String displayName;

    @Column(name = "enabled")
    Boolean enabled;

    @Enumerated(EnumType.STRING)
    SiteName value;

    @HashCodeExclude
    @ToString.Exclude
    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemEntity> items;

    //TODO Add url
}

