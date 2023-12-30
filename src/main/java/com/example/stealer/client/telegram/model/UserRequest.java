package com.example.stealer.client.telegram.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    Long chatId;
    Update update;
}