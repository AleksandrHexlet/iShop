package com.edu.ishop.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component
public class ProductManufacturer {

    @EmbeddedId
    private ProductManufactureId productManufactureId;

    private String name;
    private String country;
    private boolean isActive;

    public ProductManufacturer() {
    }

    public ProductManufacturer(ProductManufactureId productManufactureId, String name, String country, boolean isActive) {
        this.productManufactureId = productManufactureId;
        this.name = name;
        this.country = country;
        this.isActive = isActive;
    }

    public ProductManufactureId getProductManufactureId() {
        return productManufactureId;
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
