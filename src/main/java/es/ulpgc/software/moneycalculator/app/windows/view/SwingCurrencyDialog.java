package es.ulpgc.software.moneycalculator.app.windows.view;

import es.ulpgc.software.moneycalculator.architecture.model.Currency;
import es.ulpgc.software.moneycalculator.architecture.view.CurrencyDialog;

import javax.swing.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog  {
    private final List<Currency> currencies;
    private final JComboBox<Currency> selector;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
        this.add(selector = createSelector());
    }

    private JComboBox<Currency> createSelector() {
        JComboBox<Currency> comboBox = new JComboBox<>();
        currencies.forEach(comboBox::addItem);
        return comboBox;
    }

    @Override
    public Currency get() {
        return currencies.get(selector.getSelectedIndex());
    }
}
