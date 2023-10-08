package com.edu.ishop.helpers.entity;

import jakarta.persistence.*;

@Entity
@IdClass(ProductManufactureId.class)
public class ProductManufacturer {

    @Id
    private String name;
    @Id
    private String city;
    private boolean isActive = true;

    public ProductManufacturer() {
    }

    public ProductManufacturer( String name, String city, boolean isActive) {
        this.name = name;
        this.city = city;
        this.isActive = isActive;
    }



    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
