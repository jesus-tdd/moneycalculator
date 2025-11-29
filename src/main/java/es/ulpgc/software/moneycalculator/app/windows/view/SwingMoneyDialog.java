package es.ulpgc.software.moneycalculator.app.windows.view;

import es.ulpgc.software.moneycalculator.architecture.model.Currency;
import es.ulpgc.software.moneycalculator.architecture.model.Money;
import es.ulpgc.software.moneycalculator.architecture.view.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final List<Currency> currencies;
    private final TextField amount;
    private final SwingCurrencyDialog currencyDialog;

    public SwingMoneyDialog(List<Currency> currencies) {
        this.currencies = currencies;
        this.setLayout(new FlowLayout());
        this.add(amount = createAmountInput());
        this.add(currencyDialog = new SwingCurrencyDialog(currencies));
    }

    private TextField createAmountInput() {
        TextField textField = new TextField();
        textField.setColumns(4);
        return textField;
    }

    @Override
    public Money get() {
        return new Money(toDouble(amount.getText()), currencyDialog.get());
    }

    private double toDouble(String s) {
        return Double.parseDouble(s);
    }
}
