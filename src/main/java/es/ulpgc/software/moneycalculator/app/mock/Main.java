package es.ulpgc.software.moneycalculator.app.mock;

import es.ulpgc.software.moneycalculator.io.ExchangeRateLoader;
import es.ulpgc.software.moneycalculator.model.Currency;
import es.ulpgc.software.moneycalculator.model.ExchangeRate;

import java.util.List;

public class Main {
    static void main() {
        Api api = new Api();
        List<Currency> currencies = new ApiCurrencyLoader(api).loadAll();
        ExchangeRate rate = new ApiExchangeRateLoader(api).load(currencies.get(0), currencies.get(1));
        System.out.println(rate);
    }
}
