package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Family on 22.12.2016.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<Integer, Integer>();
    }

    public String getCurrencyCode() {

        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            amount += entry.getKey() * entry.getValue();
        }
        return amount;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    /**
     * 2. Логика основного метода withdrawAmount:
     * 2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
     * Используйте Жадный алгоритм (use google).
     * Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
     * Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
     * Пример, надо выдать 600
     * В манипуляторе есть следующие банкноты:
     * 500 - 2
     * 200 - 3
     * 100 - 1
     * 50 - 12
     * Результат должен быть таким:
     * 500 - 1
     * 100 - 1
     * т.е. всего две банкноты (это минимальное количество банкнот) номиналом 500 и 100.
     * <p/>
     * 2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
     * если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.
     * <p/>
     * 2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.
     * Пример, надо выдать 600
     * В манипуляторе есть следующие банкноты:
     * 500 - 2
     * 200 - 2
     * Результат - данными банкнотами невозможно выдать запрашиваемую сумму. Кинуть исключение.
     * Не забудьте, что в некоторых случаях картой кидается ConcurrentModificationException.
     *
     * @param expectedAmount
     * @return
     * @throws NotEnoughMoneyException
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {

        Map<Integer, Integer> withdrawMoney = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        List<Integer> list = new ArrayList<Integer>();
        list.addAll(denominations.keySet());
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        int tempAmount = expectedAmount;

        for (int i = 0; i < list.size(); i++) {
            int note = list.get(i);
            int count = tempAmount / note;
            if (tempAmount < note) continue;
            if (tempAmount % note == 0 && count <= denominations.get(note)) {
                withdrawMoney.put(note, count);
                tempAmount -= note * count;
                break;
            }
            int given;
            if (tempAmount % note >= list.get(list.size() - 1))  {
//            if (tempAmount % note >= list.get(list.size() - 1) && (tempAmount % note) % list.get(list.size() - 1) == 0) {

                given = Math.min(count, denominations.get(note));
            } else {
                given = Math.min(count - 1, denominations.get(note));
                if (given == 0) continue;
            }
            if (given > 0) {
                withdrawMoney.put(note, given);
                tempAmount -= note * given;
            }
        }

        if (tempAmount == 0) {
            for (Integer note : withdrawMoney.keySet()) {
                denominations.put(note, denominations.get(note) - withdrawMoney.get(note));
            }
        } else {
            throw new NotEnoughMoneyException();
        }
        return withdrawMoney;
    }

}
