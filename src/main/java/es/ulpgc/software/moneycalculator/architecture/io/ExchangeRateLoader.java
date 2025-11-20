package es.ulpgc.software.moneycalculator.architecture.io;

import es.ulpgc.software.moneycalculator.architecture.model.Currency;
import es.ulpgc.software.moneycalculator.architecture.model.ExchangeRate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
