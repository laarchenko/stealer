package com.example.stealer.model;

import com.example.stealer.enums.SubscriptionType;
import lombok.Data;

import java.util.List;

@Data
public class Subscription {

    SubscriptionType type;

    List<User> users;

    Item item;
}
