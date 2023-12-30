package com.example.stealer.service;

import com.example.stealer.enums.SubscriptionType;
import com.example.stealer.model.ItemComparisonResult;

public interface NotificationService {

    void processItemComparisonResult(ItemComparisonResult itemComparisonResult, SubscriptionType subscriptionType);
}
