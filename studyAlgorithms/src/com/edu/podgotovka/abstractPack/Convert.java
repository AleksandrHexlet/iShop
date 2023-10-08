package com.edu.podgotovka.abstractPack;

public interface Convert {
    double convert(double value);

    public static Convert getConverter(String name) {
        if (name.equals("F")) return value -> value * 1.2345 + 32;
        if (name.equals("C")) return value -> value + 234;

        return null;
    }
    public static Convert getConverter2(String name) {
        new Convert(){
            public double convert(double value){
                return  value * 1.234;
            }
        };
        return null;
    };
}
