package com.edu.podgotovka.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnIndexTwoNum {
    int[] numbers;
    int target;

    public ReturnIndexTwoNum(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
    }

    public int[] getIndexNumber(){
//        List<Integer> tempArr = new ArrayList<>();
        Map<Integer,Integer> map= new HashMap<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            if(map.containsKey(target - numbers[i]))return new int[]{map.get(numbers[i]),i};
            map.put(numbers[i],i);
       }
        return new int[]{-1,-1};
    };
}
