package com.example.stealer.client.telegram.service;

import com.example.stealer.client.telegram.sender.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {

        private final Sender botSender;

        public void sendMessage(Long chatId, String text) {
            sendMessage(chatId, text, null);
        }

        public void sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard) {
            SendMessage sendMessage = SendMessage
                    .builder()
                    .text(text)
                    .chatId(chatId.toString())
                    //Other possible parse modes: MARKDOWNV2, MARKDOWN, which allows to make text bold, and all other things
                    .parseMode(ParseMode.HTML)
                    .replyMarkup(replyKeyboard)
                    .build();
            execute(sendMessage);
        }

        private void execute(BotApiMethod botApiMethod) {
            try {
                botSender.execute(botApiMethod);
            } catch (Exception e) {
                log.error("Exception: ", e);
            }
        }
}
