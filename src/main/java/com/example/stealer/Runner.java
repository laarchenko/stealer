package com.example.stealer;

import com.example.stealer.client.telegram.Bot;
import com.example.stealer.core.ParsingTask;
import com.example.stealer.service.ItemService;
import com.example.stealer.service.ParsingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Runner {

    @SneakyThrows
    Runner(
            @Value("${scheduling.initial-delay}") Integer initialDelay,
            @Value("${scheduling.period}") Integer period,
            @Value("${scheduling.time-unit}") String timeUnit,
            ScheduledExecutorService executor,
            ItemService itemService, ParsingService parsingService,
            Bot bot) {

        executor.scheduleAtFixedRate(new ParsingTask(itemService, parsingService),
                initialDelay, period, TimeUnit.valueOf(timeUnit));

        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
    }

}
