package com.javarush.test.level26.lesson15.big01;

import java.util.*;

/**
 * Created by Family on 22.12.2016.
 */
public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> currencyManipulators = new HashMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (currencyManipulators.containsKey(currencyCode)) {
            return currencyManipulators.get(currencyCode);
        } else {
            CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
            currencyManipulators.put(currencyCode, manipulator);
            return manipulator;
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        Collection allManipulators = currencyManipulators.values();
        return allManipulators;
    }

    public static boolean hasMoney() {
        return (!currencyManipulators.isEmpty());
    }
}
