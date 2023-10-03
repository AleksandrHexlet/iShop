package com.edu.podgotovka.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trees {
    private List<Apple> apples = new ArrayList<>();
    private int countOnlyFresh;

    public void addApple(Apple apple){
        if(apple == null) throw new IllegalArgumentException("Apple не может быть null");
        if(apple.isFresh()) {
            apples.add(apple);
            countOnlyFresh = countOnlyFresh + 1;
        }
    }

     public void minusNotFresh(){
      countOnlyFresh = countOnlyFresh + 1;
  }
  public void deleteNotFreshFromList(){
//      for (int i = 0; i < apples.size(); i++) {
//         if(!apples.get(i).isFresh())
             apples.removeIf(((item)->!(item.isFresh())));
//      }
//      apples = apples.stream().filter((item)->item.isFresh()).collect(Collectors.toList());
  }


    public int getCountApple() {
//        int count = apples.stream().filter((item)->item.isFresh()).collect(Collectors.toList()).size();
        return countOnlyFresh;
    }
}
