package com.edu.ishop.controller;

import com.edu.ishop.entity.Customer;
import com.edu.ishop.entity.CustomerOrder;
import com.edu.ishop.entity.ProductCustomerOrder;
import com.edu.ishop.exceptions.ResponseException;
import com.edu.ishop.services.OrderService;
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

    @PostMapping

    public CustomerOrder postCustomerOrder(@RequestBody ProductCustomerOrder productCustomerOrder) {
        try {
            return orderService.postNewOrder(productCustomerOrder);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());

        }

    };

};
