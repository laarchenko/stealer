package com.example.stealer.client.telegram.handler.impl;

import com.example.stealer.client.telegram.handler.UserRequestHandler;
import com.example.stealer.client.telegram.helper.KeyboardHelper;
import com.example.stealer.client.telegram.model.UserRequest;
import com.example.stealer.client.telegram.service.TelegramService;
import com.example.stealer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartCommandHandler extends UserRequestHandler {

    private static final String command = "/start";
    private final TelegramService telegramService;
    private final UserService userService;
    private final KeyboardHelper keyboardHelper;
    private final ResourceBundleMessageSource messageSource;

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {

        userService.save(request.getUser());

        telegramService.sendMessage(request.getChatId(),
                messageSource.getMessage("greeting", null, request.getUser().getLocale()));
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}