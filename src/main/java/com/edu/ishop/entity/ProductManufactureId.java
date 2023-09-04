package com.edu.ishop.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class ProductManufactureId implements Serializable {
    private String nameId;
    private  String countryId;

    public ProductManufactureId() { }

    public ProductManufactureId(String nameId, String countryId) {
        this.nameId = nameId;
        this.countryId = countryId;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductManufactureId that = (ProductManufactureId) o;
        return Objects.equals(nameId, that.nameId) && Objects.equals(countryId, that.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameId, countryId);
    }
}



