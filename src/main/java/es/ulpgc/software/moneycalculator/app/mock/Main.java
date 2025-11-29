package es.ulpgc.software.moneycalculator.app.mock;

import es.ulpgc.software.moneycalculator.app.common.Api;
import es.ulpgc.software.moneycalculator.app.common.ApiCurrencyLoader;
import es.ulpgc.software.moneycalculator.app.common.ApiExchangeRateLoader;
import es.ulpgc.software.moneycalculator.architecture.model.Currency;
import es.ulpgc.software.moneycalculator.architecture.model.ExchangeRate;

import java.util.List;

public class Main {
    static void main() {
        Api api = new Api();
        List<Currency> currencies = new ApiCurrencyLoader(api).loadAll();
        ExchangeRate rate = new ApiExchangeRateLoader(api).load(currencies.get(0), currencies.get(1));
        System.out.println(rate);
    }
}
