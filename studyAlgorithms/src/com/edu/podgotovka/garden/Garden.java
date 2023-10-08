package com.edu.podgotovka.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Garden {
    private List<Trees> trees = new ArrayList<>();

    public void addTree(Trees treesOUT) {
        if (treesOUT == null) throw new IllegalArgumentException("tree ==== null");
        this.trees.add(treesOUT);
    }

    public int countApple() {
        AtomicInteger countApple = new AtomicInteger();
        trees.forEach((item) -> {
            countApple.set(countApple.get() + item.getCountApple());
        });

        return countApple.get();
    }


    public int countTrees() {
        return trees.size();
    }



}
