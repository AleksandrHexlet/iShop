package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product

{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String url_image;
    private String url;


    @ManyToOne
    Category parent;

//    @OneToMany(mappedBy = "parent")
//    private List<Product> children = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String url,String url_image, Category parent) {
        this.id = id;
        this.name = name;
        this.url_image = url_image;
        this.url = url;
        this.parent = parent;
    }

}
