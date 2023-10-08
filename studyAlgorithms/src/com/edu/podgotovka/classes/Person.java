package com.edu.podgotovka.classes;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String surname;
    private int age;
    private String eduLevel;
    private String profession;

    private final List<String> skillList = new ArrayList<>();

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }
    public Person(String name, String surname, int age, String eduLevel, String profession) {
        this(name,surname);
        this.age = age;
        this.eduLevel = eduLevel;
        this.profession = profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setProfession(){
        this.profession = "Космонавт";
    }


}
