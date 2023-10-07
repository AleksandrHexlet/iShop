package com.edu.ishop.controller;

import com.edu.ishop.entity.Customer;
import com.edu.ishop.entity.CustomerOrder;
import com.edu.ishop.entity.ProductCustomerOrder;
import com.edu.ishop.exceptions.ResponseException;
import com.edu.ishop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Provider;
import java.time.LocalDate;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;
 @Autowired
    public OrderController(OrderService orderServiceOUT){
        this.orderService =  orderServiceOUT;
    }


    @PostMapping

    public CustomerOrder postCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        try {
            return orderService.postNewOrder(customerOrder);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());

        }

    };

};
