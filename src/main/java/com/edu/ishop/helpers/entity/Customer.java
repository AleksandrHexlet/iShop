package com.edu.ishop.helpers.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy="customer")
    private List<FeedBack> feedBack = new ArrayList<>();
    private String userName;
    private String userPassword;

    private String name;
    private String city;

    @OneToMany(mappedBy="customer")
    private List<CustomerOrder> order = new ArrayList<>();


    public Customer() {
    }

    public Customer(int id, List<FeedBack> feedBack, String userName, String userPassword, String name, String city) {
        this.id = id;
        this.feedBack = feedBack;
        this.userName = userName;
        this.userPassword = userPassword;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public List<FeedBack> getFeedBack() {
        return feedBack;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFeedBack(List<FeedBack> feedBack) {
        this.feedBack = feedBack;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setOrder(List<CustomerOrder> order) {
        this.order = order;
    }
}
