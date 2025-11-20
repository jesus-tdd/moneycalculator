package es.ulpgc.software.moneycalculator.architecture.io;

import es.ulpgc.software.moneycalculator.architecture.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> loadAll();
}
