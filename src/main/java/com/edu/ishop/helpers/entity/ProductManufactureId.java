package com.edu.ishop.helpers.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;


public class ProductManufactureId implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cityStorage;

    public ProductManufactureId(String name, String cityStorage) {
        this.name = name;
        this.cityStorage = cityStorage;
    }

    public ProductManufactureId() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityStorage() {
        return cityStorage;
    }

    public void setCityStorage(String cityStorage) {
        this.cityStorage = cityStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductManufactureId that = (ProductManufactureId) o;
        return Objects.equals(name, that.name) && Objects.equals(cityStorage, that.cityStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityStorage);
    }
}



