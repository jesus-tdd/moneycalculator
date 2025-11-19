package es.ulpgc.software.moneycalculator.io;

import es.ulpgc.software.moneycalculator.model.Currency;
import es.ulpgc.software.moneycalculator.model.ExchangeRate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
