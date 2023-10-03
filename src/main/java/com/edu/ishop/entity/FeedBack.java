package com.edu.ishop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Entity
@Component
public class FeedBack {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Customer customer;
    private ZonedDateTime timeAdded;
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Product product;

    public FeedBack() {
    }

    public FeedBack(String text, Product product,Customer customerOUT) {

        this.timeAdded = ZonedDateTime.now();
        this.text = text;
        this.product = product;
        this.customer = customerOUT;

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
    public Customer getCustomer() {
        return customer;
    }
}
