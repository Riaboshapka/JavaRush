package com.javarush.test.level26.lesson15.big01;

/**
 * Created by Family on 22.12.2016.
 */
public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        if (i == 0) throw new IllegalArgumentException();
        try {
            return Operation.values()[i];
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
