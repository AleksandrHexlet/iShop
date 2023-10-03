package com.edu.podgotovka.tasks;

import java.util.Arrays;

public class Duplicate {

    public  static boolean isDuplicate(){

        int[] arrDuplicate = {1,2,3,3,3,4,5,6,7,8,9,9,9,10};
        int[] arrNotDuplicate = {1,2,3,4,5,6,7,8,9,10};
        Arrays.sort(arrDuplicate);
        int[] arr = arrDuplicate;
        for (int i = 0; i <arr.length -1 ; i++) {
            if(arr[i] == arr[i +1]) return true;
        }
        return false;
    }

    public static boolean isDuplicate2(){
        int[] arrDuplicate = {1,2,3,3,3,4,5,6,7,8,9,9,9,10};
        int[] arrNotDuplicate = {1,2,3,4,5,6,7,8,9,10};
        int[] arr = arrDuplicate;

         return Arrays.stream(arr).distinct().count() < arr.length;

    }

}
