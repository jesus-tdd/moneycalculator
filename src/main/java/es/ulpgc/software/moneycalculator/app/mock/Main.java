package es.ulpgc.software.moneycalculator.app.mock;

import es.ulpgc.software.moneycalculator.model.Currency;

import java.util.List;

public class Main {
    static void main() {
        List<Currency> currencies = new ApiCurrencyLoader().loadAll();
        for (Currency currency : currencies) {
            System.out.println(currency);
        }
    }
}
