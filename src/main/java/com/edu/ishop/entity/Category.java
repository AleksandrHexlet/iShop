package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String url;
    @ManyToOne
    private Category parent;


    public Category(String name, String url, Category parentOUT) {

        this.name = name;
        this.url = url;
        this.parent = parentOUT;
    }

    protected Category() {
    }

    //Геттеры необходимы, чтобы приватные поля попали в JSON и в последствии в Базу данных.
    // Если свойства у entity класса приватные и нет геттеров, они не попадут в json.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Category getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parent=" + parent +
                '}';
    }
}
