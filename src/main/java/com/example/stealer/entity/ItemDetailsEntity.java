package com.example.stealer.entity;

import com.example.stealer.enums.Currency;
import com.example.stealer.enums.SizeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "item_details")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @HashCodeExclude
    @ToString.Exclude
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    ItemEntity item;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    Currency currency;

    @Column(name = "size")
    Long size;

    @Column(name = "size_type")
    @Enumerated(EnumType.STRING)
    SizeType sizeType;

    @Column(name = "timestamp")
    Instant timestamp;
}
