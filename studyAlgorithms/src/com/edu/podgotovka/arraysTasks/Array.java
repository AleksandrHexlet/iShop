package com.edu.podgotovka.arraysTasks;

import java.util.*;

public class Array {

    // 1 3 5 7 9 11 13 15 5
    // 1 3 5 7 9 - под массив subArray
    // 1 5 9 13  - под последовательность  subSequence

    int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int currentSum = 0;
    int finalLenth = 0;
    int start = 0;

    public int minLengthSubArray(int target) {

        for (int end = 0; end < nums.length; end++) {
            currentSum = currentSum + nums[end];
            while (currentSum >= target) {
                finalLenth = Math.min(finalLenth, end - start + 1);
                currentSum = currentSum - nums[start];
                start++;
            }
        }
        return finalLenth;
    }
//    Найти максимальную сумму "k" смежных целых чисел в массиве целых чисел.

    public int findMaxSum(int sizeWindow) {

        if (sizeWindow > nums.length) return 0;
        int finalSum = 0;

        if (sizeWindow == nums.length) {
            for (int num : nums) {
                finalSum = finalSum + num;
            }
            return finalSum;
        }
        ;
        int sumWindow = 0;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            sumWindow = sumWindow + nums[end];
            if (end >= sizeWindow - 1) {
                finalSum = Math.max(finalSum, sumWindow);
                sumWindow = sumWindow - nums[start];
                start++;

            }
        }
        return finalSum;
    }

    ;

    public int[] mergeArrays() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums1 = {1,2,3,0,5,6};
        int[] nums2 = {2, 5, 6};
        int m1 = 3;
        int m2 = 3;

        int pointer1 = m1 - 1;
        int pointer2 = m2 - 1;
        int pointerTemp = nums1.length - 1;

        while (pointer2 >= 0) {
            if (nums2[pointer2] >= nums1[pointer1]) { // 6 > 3; 5> 3; 2>3
                nums1[pointerTemp] = nums2[pointer2];
                pointer2--;
                pointerTemp--;
            } else if (pointer1 >= 0) {
                nums1[pointerTemp] = nums1[pointer1];
                pointerTemp--;
                pointer1--;
            }
        }

        return nums1;

    }

    //    Д_Найдите самую длинную подстроку строки, содержащей различные символы

    public static String getUniqueSubStringNew(String str) {
        if (str == null || str.length() < 1) return str;

        int strLength = str.length();
        int start = 0;
        int startSubstring = 0;

        int endSubstring = 0; // индекс конца уникальной подстроки
        int end = 0;
        Set<Character> windowStorage = new HashSet<>();
        String temp = "";

//                a , б , в , б
        //    a b c b e r t
        //    c b e r t
        while (end < strLength) {
            if (!windowStorage.contains(str.charAt(end))) {
                windowStorage.add(str.charAt(end));
                end++;
                if ((endSubstring - startSubstring) < (end - start)) {
                    endSubstring = end;
                    startSubstring = start;

                }
            } else {
                windowStorage.remove(str.charAt(start));
                start++;

            }

        }
        System.out.println("result === " + str.substring(startSubstring, endSubstring));
        return str.substring(startSubstring, endSubstring);

    }

    ;


    // Метод принимает 2 строки и возвращает true or false. Если строки аннаграммы (первая строка содержит все
    // буквы второй строки только по одному разу Например String1 abcd; String 2 cdba) вернём true

    public boolean isAnnagram(String str1, String str2) {
//        abcd
//        cdba

        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        Arrays.sort(str1Arr);
        Arrays.sort(str2Arr);
        return Arrays.equals(str1Arr, str2Arr);
    }

    ;


    //    a b c b e r t
    //    r t c b e a b

    // r - 114;   t --116; c - 99; b -98; e - 101; a - 97;
    public boolean isAnnagramArray(String str1, String str2) {
        int[] arr = new int[26]; // или на 128
        // a == 97
        // z == 122


        for (int i = 0; i < str1.length(); i++) {
            arr[str1.charAt(i) - 'a'] += 100; // ячейка 0 равна 100
            arr[str2.charAt(i) - 'a'] -= 100; // ячейка 17 равна -100
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
            ;
        }
        return true;
    }

    public boolean isAnnagramMap(String str1, String str2) {
        Map<Character, Integer> map = new HashMap<>();
        // a == 97
        // z == 122
        //    a b c b e r t
        //    r t c b e a b

        for (int i = 0; i < str1.length(); i++) {
            char temp = str1.charAt(i);
//            arr[str1.charAt(i) - 'a'] += 100; // ячейка 0 равна 100
//            arr[str2.charAt(i) - 'a'] -= 100; // ячейка 17 равна -100
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        for (int i = 0; i < str2.length(); i++) {

            char temp = str2.charAt(i);
            Integer value = map.get(temp);
            if (value == null) {
                return false;
            }
            if (value == 1) {
                map.remove(temp);
            } else {
                int tempVal = map.get(temp) - 1;
                map.put(temp, tempVal);
            }

        }

        return map.isEmpty();
    }

    ;


    public boolean isPalindrom(String str1, String str2) {
        StringBuilder strReverse = new StringBuilder(str1);
        String reverse = strReverse.reverse().toString();

        return reverse.equals(str2);

    }

    //    роторАННА
    public boolean strIsPalindrom(String str1) {
        char[] charArr = str1.toCharArray();
        int start = 0;
        int end = str1.length() - 1;

        while (start <= end) {
            if (charArr[start] != charArr[end]) return false;
            start++;
            end--;
        }

        return false;
    }

//     static int startSubstr = 0;
//    static int endSubstr = 0;
//
//    public static boolean strIsPalindromWithIndex(String str1) {
//        char[] charArr = str1.toCharArray();
//
//        int start = 0;
//        int end = charArr.length -1;
//
//        while (start <= end) {
//            if (charArr[start] != charArr[end]) return false;
//            start++;
//            end--;
//        }
//
//        return true;
//    }
//
//    //    роторАННА
//
//    public static  String getLongPalindrom(String str1) {
//        char[] charArr = str1.toCharArray();
//        int start = 0;
//        for (int end = 2; end < charArr.length; end++) {
//          String str1Temp = str1.substring(start,end);
//            if (strIsPalindromWithIndex(str1.substring(start,end))) {
//                if((end - start) > (endSubstr - startSubstr)){
//                    endSubstr = end;
//                    startSubstr = start;
//                }
//                start = end;
//                end++;
//            }
//
//        }
//        System.out.println("res == "+ str1.substring(startSubstr,endSubstr));
//        return str1.substring(startSubstr,endSubstr);
//    }
//
//    ;


//    public  static int maxProfit(int[] prices) {
//        int index = 0;
//        int profit = 0;
//
//        for (int currentIndex = 0; currentIndex < prices.length; currentIndex++) {
//            if (prices[index] > prices[currentIndex]) {
//                index = currentIndex;
//            }
//            profit = Math.max(prices[currentIndex] - prices[index], profit);
//        }
//
//        return profit;
//    }

    ;

    public static boolean isStrPalindrom(String str) {
        char[] charArr = str.toCharArray();
        int start = 0;
        int end = charArr.length - 1;

        while (start <= end) {
            if (charArr[start] != charArr[end]) return false;
            start++;
            end--;
        }
        return true;

    }


    //надо взять самый длинный палиндром
    public static String getLongPalindrom(String str1) {
        if (str1 == null || str1.length() < 2) throw new IllegalArgumentException("Пустая или маленькая строка");
        char[] charArray = str1.toCharArray();

        int start = 0;

        int startPalindrom = 0;
        int endPalindrom = 0;

        for (int end = 2; end < charArray.length - 1; end++) {
            if (isStrPalindrom(str1.substring(start, end))) {
                if ((end - start) > (endPalindrom - startPalindrom)) {
                    startPalindrom = start;
                    endPalindrom = end;

                    start = end;
                    end++;
                }
            }
        }
        System.out.println(" result === " + str1.substring(startPalindrom, endPalindrom));
        return str1.substring(startPalindrom, endPalindrom);


    }

    public static int maxProfit(int[] prices) {
        int startIndex = 0;
        int profit = 0;

        for (int endIndex = 0; endIndex < prices.length; endIndex++) {
            if (prices[startIndex] > prices[endIndex]) startIndex = endIndex;

            profit = Math.max((prices[endIndex] - prices[startIndex]), profit);

        }
        return profit;
    }

    ;

    public static void main(String[] args) {
//        getUniqueSubString("Россия_Страна_Большая");
//        getUniqueSubString("Гибралтар");
//        getUniqueSubString("абв_абв_гдеклмн");
//        getUniqueSubString("абв_гдеклмн");


//        getUniqueSubStringNew("kkt");
//        getUniqueSubStringNew("ttk");
//        getUniqueSubStringNew("ktk");
//        getUniqueSubStringNew("kttql");
//        getUniqueSubStringNew("ktqql");
//        getUniqueSubStringNew("ktqllqq");
//        getUniqueSubStringNew("");
//            getUniqueSubString1("ttk");
//            getUniqueSubString("ktk");
//            getUniqueSubString("kttql");
//            getUniqueSubString("ktqql");
//            getUniqueSubString("ktqllqq");
//            getUniqueSubString("");

//        maxProfit(new int[]{20, 30, 5, 100, 59, 555});
//        getLongPalindrom("мадамнатанлетелдоход");
//        getLongPalindrom("заказдоходрадарротор");
        getLongPalindrom("аргентинаманитнеграротор");
    }

    ;


}
