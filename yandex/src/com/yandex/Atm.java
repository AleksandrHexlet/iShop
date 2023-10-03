package com.yandex;

import java.util.HashMap;
import java.util.Map;

public class Atm {

    private Map<Integer, Integer> availableCash = new HashMap<>();
    private long totalSum = 0;

    private int[] nominalATM = {50, 100, 500, 1000, 5000};

    public Atm(Map<Integer, Integer> availableCashOUT) {
        if (availableCash == null) throw new IllegalArgumentException("null");


        for (int cashNom : availableCashOUT.keySet()) {
            for (int i = 0; i < nominalATM.length - 1; i++) {
                if (nominalATM[i] != cashNom) throw new IllegalArgumentException("не верный номинал");
            }
        }
        this.availableCash = availableCash;
    }

    public void addCash(Map<Integer, Integer> newCash) {

        boolean flag = false;
        if (newCash == null) return;
        for (int cashNom : newCash.keySet()) {
            for (int i = 0; i < nominalATM.length - 1; i++) {
                if (nominalATM[i] != cashNom) {
                    flag = false;

                } else {
                    flag = true;
                    break;
                }
            }
        }
        if (flag == false) throw new IllegalArgumentException("не верный номинал");
        for (Integer key : newCash.keySet()) {
            if (key <= 0 || newCash.get(key) <= 0) return;

            if (availableCash.containsKey(key)) {
                availableCash.put(key, availableCash.get(key) + newCash.get(key));
            } else {
                availableCash.put(key, newCash.get(key));
            }
        }
    };

//    public int[] getMoney(int sum, Map<Integer, Integer> nominal) {
//
//
//
//
//    }


}
/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
//class ATM {
//    // место для кода
//}