package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Family on 22.12.2016.
 */
class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] array = ConsoleHelper.getValidTwoDigits(code);
        int denomination = Integer.valueOf(array[0]);
        int count = Integer.valueOf(array[1]);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code).addAmount(denomination, count);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination * count, code));
    }
}
