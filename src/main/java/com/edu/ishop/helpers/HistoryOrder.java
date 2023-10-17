package com.edu.ishop.helpers;

import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.CustomerOrder.OrderStatus;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.Rate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HistoryOrder {

    private Customer customer;
    private List<Product> productsList;
    private OrderStatus orderStatus;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private BigDecimal bill;
    private Rate rate;

    public HistoryOrder(Customer customer,
                        List<Product> productsList,
                        OrderStatus orderStatus,
                        LocalDate dateCreated, LocalDate dateUpdated, BigDecimal bill, Rate rate) {
        this.customer = customer;
        this.productsList = productsList;
        this.orderStatus = orderStatus;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.bill = bill;
        this.rate = rate;
    }
}
