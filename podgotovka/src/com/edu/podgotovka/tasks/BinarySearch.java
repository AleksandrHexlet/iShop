package com.edu.podgotovka.tasks;

import java.util.Arrays;

public class BinarySearch {

    public static int search(int targetNum, int[] array) {
        int firstIndex = 0;
        int lastIndex = array.length;


        while (firstIndex <= lastIndex) {
            int middle = (lastIndex - firstIndex) / 2 + 1;
            if (targetNum == array[middle]) return middle;

            if (targetNum > array[middle]) {
                firstIndex = array[middle];
            } else {
                lastIndex = array[middle];
            }

        }
        return -1;
    }

    public static int search2(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
       int target = Arrays.binarySearch(arr,2223456);
//       if(target < 0) return -1;
       return target;
    }

}
