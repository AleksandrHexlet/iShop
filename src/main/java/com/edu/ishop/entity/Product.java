package com.edu.ishop.entity;

import jakarta.persistence.ManyToOne;

public class Product

{
    private int id;
    private String name;
    private String url;
    @ManyToOne
    Category Category;
}
