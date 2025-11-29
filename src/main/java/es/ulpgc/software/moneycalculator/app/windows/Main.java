package es.ulpgc.software.moneycalculator.app.windows;

import es.ulpgc.software.moneycalculator.app.common.Api;
import es.ulpgc.software.moneycalculator.app.common.ApiCurrencyLoader;
import es.ulpgc.software.moneycalculator.app.common.ApiExchangeRateLoader;
import es.ulpgc.software.moneycalculator.architecture.control.Command;
import es.ulpgc.software.moneycalculator.architecture.control.ExchangeCommand;
import es.ulpgc.software.moneycalculator.architecture.model.Currency;

import java.util.List;

public class Main {
    private static final Api api = new Api();

    static void main() {
        List<Currency> currencies = new ApiCurrencyLoader(api).loadAll();
        MainFrame mainFrame = new MainFrame(currencies);
        Command exchange = new ExchangeCommand(
                mainFrame.moneyDialog(),
                mainFrame.currencyDialog(),
                mainFrame.moneyDisplay(),
                new ApiExchangeRateLoader(api)
        );
        mainFrame.add("exchange", exchange);
        mainFrame.setVisible(true);
    }
}
