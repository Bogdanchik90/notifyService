package com.example.notifymoney.controller;

import com.example.notifymoney.service.TinkoffBalanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestController {
    private final TinkoffBalanceService tinkoffBalanceService;

    public InvestController(TinkoffBalanceService tinkoffBalanceService) {
        this.tinkoffBalanceService = tinkoffBalanceService;
    }

    @GetMapping("/balance")
    public String getBalance() {
        return "Баланс по ИИС : " + tinkoffBalanceService.getIndividualInvestmentAccountMoney().toString() + "\n" +
                "Баланс по Основному счету : " + tinkoffBalanceService.getMainAccountMoney().toString();
    }

}
