package es.ulpgc.software.moneycalculator.io;

import es.ulpgc.software.moneycalculator.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> loadAll();
}
