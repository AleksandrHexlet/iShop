package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private BigDecimal price;
    private short rating;
//    private BigDecimal price;
//    private short rating;
    private int quantityStock;
    private LocalDate dateAdded;


    @ManyToOne
    private ProductManufacturer productManufacturer;
    @OneToOne
    private FeedBack feedBack;
    @ManyToOne
    private Category categoryProduct;

//    @OneToMany(mappedBy = "parent")
//    private List<Product> children = new ArrayList<>();

    public Product() {
    }

    public Product(String nameProduct, String urlImage, BigDecimal price, short rating, int quantityStock, LocalDate dateAdded, ProductManufacturer productManufacturer, FeedBack feedBack, Category categoryProduct) {

        this.nameProduct = nameProduct;
        this.urlImage = urlImage;
        this.price = price;
        this.rating = rating;
        this.quantityStock = quantityStock;
        this.dateAdded = dateAdded;
        this.productManufacturer = productManufacturer;
        this.feedBack = feedBack;
        this.categoryProduct = categoryProduct;
    }


    //Геттеры необходимы, чтобы приватные поля попали в JSON и в последствии в Базу данных.
    // Если свойства у entity класса приватные и нет геттеров, они не попадут в json.


    public int getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public short getRating() {
        return rating;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public ProductManufacturer getProductManufacturer() {
        return productManufacturer;
    }

    public FeedBack getFeedBack() {
        return feedBack;
    }

    public Category getCategoryProduct() {
        return categoryProduct;
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
