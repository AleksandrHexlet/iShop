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
    @Column(nullable = false)
    private String nameProduct;

    @Column(nullable = false)
    private String urlImage;
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal price;
    private double rating;
    //    private BigDecimal price;
//    private short rating;
    @Column(nullable = false)
    private int quantityStock;
    @Column(nullable = false)
    private LocalDate dateAdded;


    @ManyToOne
    private ProductManufacturer productManufacturer;
    @OneToMany(mappedBy = "product")
    private List<FeedBack> feedBack;
    @ManyToOne
    private Category categoryProduct;

//    @OneToMany(mappedBy = "parent")
//    private List<Product> children = new ArrayList<>();

    public Product() {
    }

    public Product(String nameProduct, String urlImage, BigDecimal price, double rating, int quantityStock, LocalDate dateAdded, ProductManufacturer productManufacturer, Category categoryProduct) {

        this.nameProduct = nameProduct;
        this.urlImage = urlImage;
        this.price = price;
        this.rating = rating;
        this.quantityStock = quantityStock;
        this.dateAdded = dateAdded;
        this.productManufacturer = productManufacturer;

        this.categoryProduct = categoryProduct;
    }

    public void setFeedbackToList(FeedBack feedBackOUT) {
        this.feedBack.add(feedBackOUT);
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

    public double getRating() {
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
