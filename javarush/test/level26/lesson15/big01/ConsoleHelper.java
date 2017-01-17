package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Family on 22.12.2016.
 */
public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a line of text.  A line is considered to be terminated by any one
     * of a line feed ('\n'), a carriage return ('\r'), or a carriage return
     * followed immediately by a linefeed.
     *
     * @return     A String containing the contents of the line, not including
     *             any line-termination characters, or null if the end of the
     *             stream has been reached or id an I/O error occurs
     *
     * @throws InterruptOperationException If input string is equal "EXIT" in any case
     */
    public static String readString() throws InterruptOperationException {
        String answer;
        try {
            answer = reader.readLine();
        } catch (IOException e) {
            return null;
        }
        if ("EXIT".equalsIgnoreCase(answer)) throw new InterruptOperationException();
        return answer;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
    String currencyCode = readString();
        while (!currencyCode.matches("[A-Za-z]{3}")) {
            writeMessage(res.getString("invalid.data"));
            currencyCode = readString();
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String input = readString();
        while (!input.matches("^([1-9][\\d]*) ([1-9][\\d]*)$")) {
            writeMessage(res.getString("invalid.data"));
            input = readString();
        }
        String[] result = input.split(" ");
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation") + ": 1 - " + res.getString("operation.INFO") +
                ", 2 - " + res.getString("operation.DEPOSIT") + ", 3 - " + res.getString("operation.WITHDRAW") +
                ", 4 - " + res.getString("operation.EXIT"));
        String operation = readString();
        while (true) {
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                operation = readString();
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
