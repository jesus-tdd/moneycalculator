package es.ulpgc.software.moneycalculator.app.windows;

import es.ulpgc.software.moneycalculator.app.windows.view.SwingCurrencyDialog;
import es.ulpgc.software.moneycalculator.app.windows.view.SwingMoneyDialog;
import es.ulpgc.software.moneycalculator.app.windows.view.SwingMoneyDisplay;
import es.ulpgc.software.moneycalculator.architecture.control.Command;
import es.ulpgc.software.moneycalculator.architecture.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final List<Currency> currencies;
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final Map<String, Command> commands;

    public SwingMoneyDialog moneyDialog() {
        return moneyDialog;
    }

    public SwingCurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    public SwingMoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    public MainFrame(List<Currency> currencies) {
        this.currencies = currencies;

        this.setTitle("Money Calculator");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.add(moneyDialog = createMoneyDialog());
        this.add(currencyDialog = createCurrencyDialog());
        this.add(moneyDisplay = createMoneyDisplay());
        this.add(createCalculateButton());
        this.commands = new HashMap<>();
    }

    private JButton createCalculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("exchange").execute();
            }
        });
        return button;
    }

    private SwingMoneyDisplay createMoneyDisplay() {
        return new SwingMoneyDisplay();
    }

    private SwingCurrencyDialog createCurrencyDialog() {
        return new SwingCurrencyDialog(currencies);
    }

    private SwingMoneyDialog createMoneyDialog() {
        return new SwingMoneyDialog(currencies);
    }

    public void add (String operation, Command command) {
        this.commands.put(operation, command);
    }
}
