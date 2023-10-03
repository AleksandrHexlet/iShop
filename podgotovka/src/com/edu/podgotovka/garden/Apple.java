package com.edu.podgotovka.garden;

public class Apple {
   private boolean isFresh = true;

    public boolean isFresh() {
        return isFresh;
    }

    public void makeNotFresh(){
        isFresh = false;
    }

}
