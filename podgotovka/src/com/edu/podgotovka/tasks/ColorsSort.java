package com.edu.podgotovka.tasks;

public class ColorsSort {
    static int[] arr = {0,0,1,0,2,0,2,1,0,2,1,0};

    public static void sort(){
        int zeroPointer = 0;
        int onePointer = 0;
        int twoPointer = 0;
           while(onePointer <= twoPointer) {
           if(arr[onePointer]== 0){
              arr[zeroPointer] = arr[onePointer];
              arr[onePointer] = 0;
              zeroPointer++;
              onePointer++;
// 1,1,1,0,0,2,0,2,1,2
           } else if(arr[onePointer] == 1){
               onePointer++;

           } else if(arr[onePointer] == 2) {
               arr[onePointer] = arr[twoPointer];
               arr[twoPointer] = 2;
               twoPointer--;

           }

        }
    }

}
