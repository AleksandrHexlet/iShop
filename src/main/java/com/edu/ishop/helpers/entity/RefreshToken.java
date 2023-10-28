package com.edu.ishop.helpers.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RefreshToken extends IdData {

    private String value;
    private LocalDate expiredDate = LocalDate.now().plusMonths(1);
    @OneToOne
    private Customer customer;

    public RefreshToken(String value, Customer customer) {
        this.value = value;
        this.customer = customer;
    }

    public RefreshToken() {
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
