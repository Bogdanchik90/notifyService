package com.example.notifymoney.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.Account;
import ru.tinkoff.piapi.core.InvestApi;
import ru.ttech.piapi.core.connector.ConnectorConfiguration;

@Service
public class TinkoffBalanceService {

    private final ConnectorConfiguration configuration =
            ConnectorConfiguration.loadPropertiesFromResources("invest.properties");
    private final String token = configuration.getToken();
    InvestApi api = InvestApi.create(token);

    /**
     * получаем баланс по основному счету
     */
    public Integer getMainAccountMoney() {
        Account account = api.getUserService().getAccountsSync().get(1);
        var portfolio = api.getOperationsService().getPortfolioSync(account.getId());
        return portfolio.getTotalAmountPortfolio().getValue().intValue();
    }

    /**
     * получаем баланс по ИИС
     */
    public Integer getIndividualInvestmentAccountMoney() {
        Account account = api.getUserService().getAccountsSync().get(0);
        var portfolio = api.getOperationsService().getPortfolioSync(account.getId());
        return portfolio.getTotalAmountPortfolio().getValue().intValue();
    }

}
