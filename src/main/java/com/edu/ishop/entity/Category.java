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
