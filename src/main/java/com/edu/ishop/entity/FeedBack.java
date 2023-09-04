package com.edu.ishop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Entity
@Component
public class FeedBack {

    @Id
    @GeneratedValue
    int id;
    ZonedDateTime timeAdded;
    String text;

    @OneToOne
    Product product;

    public FeedBack() {
    }

    public FeedBack(ZonedDateTime timeAdded, String text, Product product) {

        this.timeAdded = timeAdded;
        this.text = text;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public ZonedDateTime getTimeAdded() {
        return timeAdded;
    }

    public String getText() {
        return text;
    }

    public Product getProduct() {
        return product;
    }
}
