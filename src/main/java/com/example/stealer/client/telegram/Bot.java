package com.example.stealer.client.telegram;

import com.example.stealer.client.telegram.model.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

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
            String textFromUser = update.getMessage().getText();

            Long userId = update.getMessage().getFrom().getId();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);

            Long chatId = update.getMessage().getChatId();

            UserRequest userRequest = UserRequest
                    .builder()
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
