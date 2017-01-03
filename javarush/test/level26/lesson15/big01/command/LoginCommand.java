package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Family on 28.12.2016.
 */
class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");


    /**
     * 3. Создадим LoginCommand по аналогии с другими командами, в котором захардкодим номер карточки с пином
     * 123456789012 и 1234 соответственно.
     * 4. Реализуйте следующую логику для команды LoginCommand:
     * 4.1. Пока пользователь не введет валидные номер карты и пин — выполнять следующие действия
     * 4.2. Запросить у пользователя 2 числа — номер кредитной карты, состоящий из 12 цифр, и пин — состоящий из 4 цифр
     * 4.3. Вывести юзеру сообщение о невалидных данных, если они такими являются.
     * 4.4. Если данные валидны, то проверить их на соответствие захардкоженным (123456789012 и 1234).
     * 4.5. Если данные в п. 4.4. идентифицированы, то сообщить, что верификация прошла успешно.
     * 4.6. Если данные в п. 4.4. НЕ идентифицированы, то вернуться к п.4.1.
     *
     * @throws InterruptOperationException
     */
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String answer;

        while (true) {
            answer = ConsoleHelper.readString();
            if (!answer.matches("^[\\d]{12} [\\d]{4}$")) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            String[] pair = answer.split(" ");
            if (validCreditCards.containsKey(pair[0]) && pair[1].equals(validCreditCards.getString(pair[0]))) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), pair[0]));
                return;
            }

            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), pair[0]));
        }
    }
}
