package com.edu.podgotovka;

import com.edu.podgotovka.safeThread.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> strList  = new LinkedList<>();

        strList.addNode("1 элемент");
        strList.addNode("2 элемент");
        strList.addNode(null);
        strList.addNode("3 элемент");
        strList.addNode("4  элемент");
        strList.addNode("5 элемент");
        strList.addNode("6 элемент");
        strList.addNode("7 элемент");
//        strList.addNode("8 элемент");
        strList.reverse();
        System.out.println(" длина  === "+ strList.getNodeLength());
        System.out.println(" element 0 === "+ strList.getElementByIndex(0));
        System.out.println(" element 1  === "+ strList.getElementByIndex(1));
        System.out.println(" element 2  === "+ strList.getElementByIndex(2));
        System.out.println(" element 3  === "+ strList.getElementByIndex(3));
        System.out.println(" element 4  === "+ strList.getElementByIndex(4));
        System.out.println(" element 5  === "+ strList.getElementByIndex(5));
        System.out.println(" element 6  === "+ strList.getElementByIndex(6));
        System.out.println(" element 7  === "+ strList.getElementByIndex(7));


        System.out.println(" element средний  === "+ strList.getMiddleElement());
    }
}
