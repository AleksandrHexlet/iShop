package com.edu.podgotovka.arraysTasks;

import java.util.ArrayList;
import java.util.List;

public class ArrayObserver {
 List<Observer> observerList = new ArrayList<>();

 public void addObserver(Observer observer){
     observerList.add(observer);
 }
 public void removeObserver(Observer observer){
     observerList.remove(observer);
 }

   public void setTextInConsole(){
     System.out.println("Text");
       for (Observer observer : observerList) {
           observer.reaction();
       }
 }

}
