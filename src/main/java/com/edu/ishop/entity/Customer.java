package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "Customer")
public class Customer {
    @Id
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
}
