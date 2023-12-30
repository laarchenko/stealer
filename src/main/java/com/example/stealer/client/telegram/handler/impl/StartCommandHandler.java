package com.example.stealer.client.telegram.handler.impl;

import com.example.stealer.client.telegram.handler.UserRequestHandler;
import com.example.stealer.client.telegram.helper.KeyboardHelper;
import com.example.stealer.client.telegram.model.UserRequest;
import com.example.stealer.client.telegram.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
@RequiredArgsConstructor
public class StartCommandHandler extends UserRequestHandler {

    private static final String command = "/start";
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        ReplyKeyboard replyKeyboard = keyboardHelper.buildMainMenu();
        telegramService.sendMessage(request.getChatId(),
                "\uD83D\uDC4BПривіт! За допомогою цього чат-бота ви зможете зробити запит про допомогу!",
                replyKeyboard);
        telegramService.sendMessage(request.getChatId(),
                "Обирайте з меню нижче ⤵️");
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}