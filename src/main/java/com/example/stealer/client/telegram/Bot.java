package com.example.stealer.client.telegram;

import com.example.stealer.client.telegram.model.UserRequest;
import com.example.stealer.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    @Value("${client.telegram.username}")
    private String username;

    @Value("${client.telegram.token}")
    private String token;

    private final Dispatcher dispatcher;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {

            var message = update.getMessage();
            var textFromUser = message.getText();

            var user = message.getFrom();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            log.info("[{}, {}] : {}", user.getId(), userFirstName, textFromUser);

            Long chatId = update.getMessage().getChatId();

            UserRequest userRequest = UserRequest
                    .builder()
                    .user(User.builder()
                            .name(user.getFirstName())
                            .lastname(user.getLastName())
                            .telegramId(user.getId())
                            .telegramUsername(user.getUserName())
                            .locale(Locale.forLanguageTag(user.getLanguageCode()))
                            .build())
                    .update(update)
                    .chatId(chatId)
                    .build();

            boolean dispatched = dispatcher.dispatch(userRequest);

            if (!dispatched) {
                log.warn("Unexpected update from user");
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
