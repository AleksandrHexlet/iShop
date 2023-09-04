package com.edu.ishop.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@IdClass(ProductManufactureId.class)
public class ProductManufacturer {

    @Id
    private String name;
    @Id
    private String country;
    private boolean isActive;

    public ProductManufacturer() {
    }

    public ProductManufacturer( String name, String country, boolean isActive) {
        this.name = name;
        this.country = country;
        this.isActive = isActive;
    }



    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public boolean isActive() {
        return isActive;
    }
}
