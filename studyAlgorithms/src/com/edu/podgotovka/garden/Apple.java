package com.edu.podgotovka.garden;

import com.edu.podgotovka.arraysTasks.Observer;

public class Apple implements Observer {
   private boolean isFresh = true;

    public boolean isFresh() {
        return isFresh;
    }

    public void makeNotFresh(){
        isFresh = false;
    }

    @Override
    public void reaction() {
        System.out.println("Apple");
    }
}
