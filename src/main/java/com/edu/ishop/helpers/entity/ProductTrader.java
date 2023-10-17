package com.edu.ishop.helpers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@IdClass(ProductManufactureId.class)
public class ProductTrader {

    //    @Id
    @Size(min = 2, max = 199, message = "Длина имени категории должна быть равен или более 2 символам и менее 199 символов")
    private String name;
    @Id
    private String userName;
    private LocalDate dateRegistration;
    private double rating;
    @Positive()
    @DecimalMin(value = "1")
    private BigDecimal traderBill;
    private int rate;


    //    @Id
    private String cityStorage;
    private boolean isActive = true;

    public ProductTrader() {
    }

    public ProductTrader(String name, String userName, LocalDate dateRegistration,
                         double rating, BigDecimal traderBill, int rate, String cityStorage,
                         boolean isActive) {
        this.name = name;
        this.userName = userName;
        this.dateRegistration = dateRegistration;
        this.rating = rating;
        this.traderBill = traderBill;
        this.rate = rate;
        this.cityStorage = cityStorage;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return cityStorage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getDateRegistration() {
        return dateRegistration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public BigDecimal getTraderBill() {
        return traderBill;
    }

    public void setTraderBill(BigDecimal traderBill) {
        this.traderBill = traderBill;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCityStorage() {
        return cityStorage;
    }

    public void setCityStorage(String cityStorage) {
        this.cityStorage = cityStorage;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}

