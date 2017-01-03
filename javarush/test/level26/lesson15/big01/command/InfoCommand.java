package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Family on 22.12.2016.
 */
class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        if (!CurrencyManipulatorFactory.hasMoney()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
            return;
        }
        Collection<CurrencyManipulator> allManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        for (CurrencyManipulator manipulator : allManipulators) {
            System.out.println(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
        }
    }
}
