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
    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();



    public Category(String name, String url,Category parentOUT) {

        this.name = name;
        this.url = url;
        this.parent = parentOUT;
    }

    public Category() {

    }

}
