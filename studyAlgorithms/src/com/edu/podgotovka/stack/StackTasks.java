package com.edu.podgotovka.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackTasks {


    public static boolean isValidParentheses(String str) {
        char[] charStr = str.toCharArray();
        Stack<Character> stack = new Stack();
//        (  === )  { == }
//        [      ]

        for (char charArrItem : charStr) {
            if (charArrItem == '(') {
                stack.push(')');
            } else if (charArrItem == '{') {
                stack.push('}');
            } else if (charArrItem == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || charArrItem != stack.pop()) {            //  strValid="{}()[]"  строчку с валидными скобками
                return false;                                                     // strNonValid="{){][)"//                                          //    stack = }
            }
        }
        ;

        return stack.isEmpty();
    }

    ;
//  index       [0,  1  2  3 4   5  6  7]

//  temperature [10,20,15,40,9,  8, 9, 3]
//  stack       [3,1,0]
//  answer      [1, 2  1  0  0   1  0  0 ]
//
    //используем Stack и перебираем массив с конца
    public static int[] waitWear(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int end = temperatures.length - 1; end < 1; end--) {
         while(!stack.isEmpty() && temperatures[end] >= temperatures[stack.peek()]){
             stack.pop();
         }
            if(!stack.isEmpty()) answer[end] = stack.peek() - end;

            stack.push(end);

        }
        return answer;
    }




//  index       [0,  1  2  3 4   5  6  7]

//  temperature [10,20,15,40, 9,  8, 9, 3]

//  tempIndex   [          3 ]


//  answer      [1, 2  1,      ]




//  stack       [3,1,0]
//  answer      [1, 2  1  0  0   1  0  0 ]
//  answer      [0, 1  0  1  0   1  0  0 ]

//    public static int[] howDaysFromUpTemperature(int[] temperatures){
//        int[]answer = new int[temperatures.length];
//        Queue<Integer> indexTemp = new LinkedList<>();
//        for (int i = 0; i < temperatures.length; i++) {
//            while (!indexTemp.isEmpty() && temperatures[i] > temperatures[indexTemp.peek()]) {
//                answer[indexTemp.peek()] = i - indexTemp.peek();
//                indexTemp.poll(); // удаляем элемент с начала. Если элемента нет, тогда вернем null
//            }
//            indexTemp.add(i);
//        }
//        return answer;
//    };
//

    //  index   [0,  1  2  3 4   5  6  7]

//  temperature [10,20,15,40, 9,  8, 9, 3]

//  tempIndex   [          3 ]


//  answer      [1, 2  1,      ]

// вычисляем количество дней когда потеплеет

    public static int[] whenWarm(int[] temperatures){
        int[] answer = new int[temperatures.length];
        Queue<Integer> indexTemp = new LinkedList<>();

        for (int i = 0; i < temperatures.length; i++) {
          while (!indexTemp.isEmpty() && temperatures[i] > temperatures[indexTemp.peek()]){
              answer[indexTemp.peek()] = i - indexTemp.peek();
              indexTemp.poll();
          }
          indexTemp.add(i);
        }
        return answer;
    }

    ;
    // stackStart  [1,  2,  3]
    // stackEnd    [3,  2,  1]
    public static class Myqueue {
        Stack<Integer> stackStart = new Stack<>();
        Stack<Integer> stackEnd = new Stack<>();


        public void push(int num){
//            добавить в конец
            stackStart.push(num);

        }
        public int pop(){
//            вернет число и удалит его с начала очереди
            int temp = peek();
            stackEnd.pop();
            return temp;
        }
        public int peek(){
            while (!stackEnd.isEmpty()){
                stackEnd.push(stackStart.pop());
            }
//            вернет число с начала очереди
            return stackEnd.peek();

        }

        public boolean empty(){
     return  stackStart.isEmpty() && stackEnd.isEmpty();

        };
    }


//    Реализовать структуру стек (MyStack), используя 2 однонаправленные очереди и их методы (queue01 и queue02)

    public static class MyStack {
        private Queue<Integer> queue01;
        private Queue<Integer> queue02;

        public MyStack() {
            queue01 = new LinkedList<>();
            queue02 = new LinkedList<>();
        }

        public void push(int item) {
            queue01.add(item);
        }

        public int pop() { //удаляет элемент с вершины (конца) и возвращает его значение
            if (queue01.isEmpty()) {
                return -1;
            }
            // queue01 1 2 3 4
            while (queue01.size() > 1) {//берём с начала queue01 и добавляем в конец queue02, все элементы кроме первого
                queue02.add(queue01.remove()); //
            }
            // queue01 4
            // queue02 1 2 3
            // queue01
            int result = queue01.remove();

            swapQueues();
            return result; // return 4
        }

        public int peek() { // возвращает элемент с вершины (конца) и не удаляет его
            if (queue01.isEmpty()) {
                return -1; // or throw an exception
            }
            while (queue01.size() > 1) {//берём с начала queue01 и добавляем в конец queue02, все элементы кроме первого
                queue02.add(queue01.remove());

            }
            // queue01 4
            // queue02 1 2 3
            int result = queue01.peek();
            queue02.add(queue01.remove());
            swapQueues();
            return result;
        }

        public boolean empty() { // boolen true/false
            return queue01.isEmpty();
        }

        private void swapQueues() {
            Queue<Integer> temp = queue01;
            queue01 = queue02;
            queue02 = temp;
        }
    }

}