package com.edu.ishop.client.controller;

import com.edu.ishop.helpers.entity.CustomerOrder;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.client.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
