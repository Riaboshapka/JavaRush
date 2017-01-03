package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by Family on 22.12.2016.
 */
public class CashMachine {
    public static final String RESOURCE_PATH = "com.javarush.test.level26.lesson15.big01.resources.";

    public static void main(String[] args) {
//        Locale.setDefault(Locale.ENGLISH);
//        Locale.setDefault(new Locale("ua", "UA"));
        Language.chooseLanguage();
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            while (true){
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
