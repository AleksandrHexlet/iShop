package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class CustomerOrder {
    @Id
    private int id;
    @ManyToOne
    private Customer customer;

    private LocalDate date;
    private LocalDate dateUpdateStatus;
    private OrderStatus status;
    private String deliveryAddress;

    public enum OrderStatus{
        ORDER_ACCEPTED,
        ORDER_FORMED,
        ORDER_PAID,
        ORDER_SENT_BUYER,
        ORDER_DELIVERED,
        ORDER_CANCELLED,


    }

    public CustomerOrder() {
    }

    public CustomerOrder(Customer customer, LocalDate date, LocalDate dateUpdateStatus, OrderStatus status, String deliveryAddress) {
        this.customer = customer;
        this.date = date;
        this.dateUpdateStatus = dateUpdateStatus;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDateUpdateStatus() {
        return dateUpdateStatus;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
