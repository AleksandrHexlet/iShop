package com.edu.podgotovka.safeThread;

import java.util.HashSet;
import java.util.List;

public class LinkedList<T> {
    private Node<T> first;
    private Node<T> last;

    private int nodeLength;


//    public synchronized void addNode(T element) {
//        Node<T> node = new Node<>(element);
//        if (first == null) {
//            first = node;
//            nodeLength = 1;
//        } else {
//            Node<T> temporary = first;
//            for (int i = 0; i <= nodeLength; i++) {
//                if (temporary.nodeNext != null) {
//                    temporary = temporary.nodeNext;
//                } else {
//                temporary.nodeNext = node;
//                    nodeLength = nodeLength +1;
//                }
//            }
//        }
//    }

    public int getNodeLength() {
        return nodeLength;
    }

    static class Node<T> {
        private T element;
        private Node<T> nodeNext;
        private Node<T> nodePrevios;

        public Node(T element) {
            this.element = element;

        }

    }

    public synchronized void addNode(T element) {
        Node<T> node = new Node<>(element);

        if (first == null) {
            first = node;
            last = node;
            first.nodeNext = last;
            last.nodePrevios = first;
        } else {
            last.nodeNext = node; // ссылка на ноду
            node.nodePrevios = last;
            last = node;
        }
        nodeLength++;
        first.nodePrevios = null;
        last.nodeNext = null;
    }

//    public T getElementByIndex(int index) {
//        if (index < 0 || index > nodeLength) throw new ArrayIndexOutOfBoundsException("За пределами");
//        Node<T> temp = first;
//        for (int i = 0; i < nodeLength; i++) {
//            if (index == i) {
//                return temp.element;
//            } else {
//                temp = temp.nodeNext;
//            }
//        }
//        return null;
//    }

    public T getElementByIndex(int index) {
        if (index < 0 || index > nodeLength) throw new ArrayIndexOutOfBoundsException("qq");
        Node<T> tempStart = first;
        Node<T> tempEnd = last;
        int middle = nodeLength / 2 + 1;
        if (index >= middle) {
            for (int i = nodeLength -1 ; i >= middle; i--) {
                if (i == index) {
                    return tempEnd.element;
                } else {
                    tempEnd = tempEnd.nodePrevios;
                }
            }
        } else {
            for (int i = 0; i < middle; i++) {
                if (i == index) {
                    return tempStart.element;
                } else {
                    tempStart = tempStart.nodeNext;
                }
            }
        }

        return null;
    }

//    public T getMiddleElement() {
//        if (first == null) return null;
//        Node<T> temp = first;
//        int length = 0;
//        while (temp.nodeNext != null) {
//            temp = temp.nodeNext;
//            length++;
//        }
//        if(length % 2 == 0) {
//            return getElementByIndex(length / 2 + 1);
//        } else {
//            return getElementByIndex(length / 2 );
//        }
//    }

    public T getMiddleElement() {
        if (first == null) return null;
        Node<T> everyStep = first;
        Node<T> overStep = first; // шагаем через один елемент;

        while (overStep != null && overStep.nodeNext != null) {
            everyStep = everyStep.nodeNext;
            overStep = overStep.nodeNext.nodeNext;
        }
        return everyStep.element;

    }

//    public T reverse() {
//        if (first == null) return null;
//        Node<T> firstElement = new Node<>(first.element);
//        Node<T> temp = first;
//        Node<T> end = null;
//
//        while (temp != end) {
//            Node<T> ElementNext = temp.nodeNext; // 1
//            Node<T> Elementprevios = firstElement.nodeNext;      // 1
//
//            firstElement.nodeNext = temp; // 1 = 0
//            temp.nodeNext = Elementprevios; // 1 = 1
//
//            temp = ElementNext; // 1 = 2
//        }
//        return null;
//    }
    public void reverse() {
        if (first == null) return;
        Node<T> firstElement = null; // 1 елемент
        Node<T> stepOnTree = first;

        while (stepOnTree != null) {
            Node<T> ElementNext = stepOnTree.nodeNext; // 2 елемент
            stepOnTree.nodeNext = firstElement;  // второй елемент = первому елементу
            firstElement = stepOnTree;
            stepOnTree = ElementNext;
        }
        first = firstElement;
    }

    public boolean isCircle(){
        if (first == null) return false;
        Node<T> everyStep = first;
        Node<T> overStep = first; // шагаем через один елемент;

        while (overStep != null && overStep.nodeNext != null) {
            everyStep = everyStep.nodeNext;
            overStep = overStep.nodeNext.nodeNext;
            if(everyStep == overStep) return true;
        }
        return false;
    }

    public boolean isCircleHashset(){
        if(first == null) return  false;
        HashSet<Node> set = new HashSet<>();
        Node<T> start  = first;
        while (start != null){
           if(set.add(start)){
               start = start.nodeNext;
           } else {
               return  true;
           }
        }
        return false;
    };




//    public T reverse(){
//        if(first == null) return null;
//        Node<T>firstElement = new Node<>(first.element);
//        Node<T> temp = first;
//        Node<T> end = null;
//
//        while(temp != end ){
//            Node<T>ElementNext = temp.nodeNext; // 1
//            Node<T>Elementprevios = firstElement.nodeNext;      // 1
//
//            firstElement.nodeNext =  temp; // 1 = 0
//            temp.nodeNext = Elementprevios; // 1 = 1
//
//            temp = ElementNext; // 1 = 2
//        }
//        return null;
//    }
}

//    Given the head of a singly linked list, return the middle node of the linked list.
//        If there are two middle nodes, return the second middle node.
//    Реализовать потокобезопасный
//    двунаправленный или однонаправленный связный список
//    с методами: T get(int index),  void add(T element)