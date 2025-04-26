package com.example.notifymoney.scheduler;

import com.example.notifymoney.service.TelegramNotifier;
import com.example.notifymoney.service.TinkoffBalanceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TinkoffBalanceScheduler {
    private final TelegramNotifier telegramNotifier;
    private final TinkoffBalanceService tinkoffBalanceService;

    public TinkoffBalanceScheduler(TelegramNotifier telegramNotifier, TinkoffBalanceService tinkoffBalanceService) {
        this.telegramNotifier = telegramNotifier;
        this.tinkoffBalanceService = tinkoffBalanceService;
    }

    //    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 17 * * ?", zone = "Europe/Moscow")
    public void fetchBalanceAndNotify() {
        StringBuilder message = new StringBuilder("💰 Баланс Тинькофф Инвестиции:\n");

        message.append("Баланс по ИИС : " + tinkoffBalanceService.getIndividualInvestmentAccountMoney().toString() + "\n" +
                "Баланс по Основному счету : " + tinkoffBalanceService.getMainAccountMoney().toString());

        telegramNotifier.sendNotification(message.toString());
    }
}