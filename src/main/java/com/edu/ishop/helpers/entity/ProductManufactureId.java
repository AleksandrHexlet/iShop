package com.edu.ishop.helpers.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;


public class ProductManufactureId implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;

    public ProductManufactureId(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public ProductManufactureId() {
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductManufactureId that = (ProductManufactureId) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}



