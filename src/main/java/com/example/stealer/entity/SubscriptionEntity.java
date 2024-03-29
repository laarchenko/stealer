package com.example.stealer.entity;

import com.example.stealer.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    SubscriptionType type;

    @ManyToMany(mappedBy = "subscriptions")
    List<UserEntity> users;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    ItemEntity item;
}
