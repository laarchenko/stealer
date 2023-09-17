package com.example.stealer.service.impl;

import com.example.stealer.model.ItemComparisonResult;
import com.example.stealer.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void processItemComparisonResult(ItemComparisonResult itemComparisonResult) {
        System.out.println(itemComparisonResult);
    }
}
