package es.ulpgc.software.moneycalculator.architecture.control;

import es.ulpgc.software.moneycalculator.architecture.io.ExchangeRateLoader;
import es.ulpgc.software.moneycalculator.architecture.model.Currency;
import es.ulpgc.software.moneycalculator.architecture.model.ExchangeRate;
import es.ulpgc.software.moneycalculator.architecture.model.Money;
import es.ulpgc.software.moneycalculator.architecture.view.CurrencyDialog;
import es.ulpgc.software.moneycalculator.architecture.view.MoneyDialog;
import es.ulpgc.software.moneycalculator.architecture.view.MoneyDisplay;

import java.time.LocalDate;

public class ExchangeCommand implements Command{
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final MoneyDisplay moneyDisplay;
    private final ExchangeRateLoader loader;

    public ExchangeCommand(
            MoneyDialog moneyDialog,
            CurrencyDialog currencyDialog,
            MoneyDisplay moneyDisplay,
            ExchangeRateLoader loader
    ) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
        this.loader = loader;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();

        ExchangeRate exchangeRate = loader.load(money.currency(), currency);
        Money result = new Money(money.amount() * exchangeRate.rate(), currency);

        moneyDisplay.show(result);
    }
}
