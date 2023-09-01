package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String nameProduct;
    private String urlImage;



    @ManyToOne
    private Category categoryProduct;

//    @OneToMany(mappedBy = "parent")
//    private List<Product> children = new ArrayList<>();

    public Product() {}

    public Product(String name, String url_image, Category categoryProduct) {
        this.nameProduct = name;
        this.urlImage = url_image;
        this.categoryProduct = categoryProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", categoryProduct=" + categoryProduct +
                '}';
    }
}
