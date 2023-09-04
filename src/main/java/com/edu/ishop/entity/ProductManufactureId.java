package com.edu.ishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.Objects;



public class ProductManufactureId implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  String country;


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductManufactureId that = (ProductManufactureId) o;
        return Objects.equals(name, that.name) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}



