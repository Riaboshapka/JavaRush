package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Family on 22.12.2016.
 * 1. Реализуйте следующий алгоритм для команды WithdrawCommand:
 * 1.1. Считать код валюты (метод уже есть)
 * 1.2. Получить манипулятор для этой валюты.
 * 1.3. Пока пользователь не введет корректные данные выполнять:
 * 1.3.1. Попросить ввести сумму
 * 1.3.2. Если введены некорректные данные, то сообщить об этом пользователю и вернуться к п. 1.3.
 * 1.3.3. Проверить, достаточно ли средств на счету.
 * Для этого в манипуляторе создайте метод boolean isAmountAvailable(int expectedAmount), который вернет true, если денег достаточно для выдачи.
 * Если недостаточно, то вернуться к п. 1.3.
 * 1.3.4. Списать деньги со счета. Для этого в манируляторе создайте метод
 * Map<Integer, Integer> withdrawAmount(int expectedAmount), который вернет карту Map<номинал, количество>.
 * Подробно логику этого метода смотрите в п.2.
 * 1.3.5. Вывести пользователю результат из п. 1.3.4. в следующем виде:
 * <табуляция>номинал - количество
 * Сортировка - от большего номинала к меньшему.
 * Вывести сообщение об успешной транзакции.
 * 1.3.6. Перехватить исключение NotEnoughMoneyException, уведомить юзера о нехватке банкнот и вернуться к п. 1.3.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        do {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String readString = ConsoleHelper.readString();
            int amount;
            try {
                amount = Integer.parseInt(readString);
                if (amount <= 0) throw  new NumberFormatException();
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(amount)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            Map<Integer,Integer> withdrawMoney;
            try {
                withdrawMoney = manipulator.withdrawAmount(amount);
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            for (Map.Entry<Integer, Integer> entry : withdrawMoney.entrySet()) {
                ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
            }
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, code));
            break;
        } while(true);
    }
}
