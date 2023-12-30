package com.example.stealer.client.telegram.handler.impl;

import com.example.stealer.client.telegram.handler.UserRequestHandler;
import com.example.stealer.client.telegram.helper.KeyboardHelper;
import com.example.stealer.client.telegram.model.UserRequest;
import com.example.stealer.client.telegram.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
@RequiredArgsConstructor
public class TextHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate());
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildMainMenu();
        telegramService.sendMessage(userRequest.getChatId(),"Дякую, ваше звернення було зареєстровано!", replyKeyboardMarkup);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
