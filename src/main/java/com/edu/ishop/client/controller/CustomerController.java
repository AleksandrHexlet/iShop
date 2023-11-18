package com.edu.ishop.client.controller;

import com.edu.ishop.client.services.CustomerService;
import com.edu.ishop.client.services.TokenResponse;
import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.exceptions.ResponseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService; //агрегация

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/registration")
    public ResponseEntity<String> customerRegistration(@Valid @RequestBody Customer customer) {

        try {
            customerService.customerRegistration(customer);
        } catch (ResponseException e) {

            return ResponseEntity.badRequest().body("Клиент не был сохраненён в БД");
        }
        return ResponseEntity.ok("Клиент сохранён успешно");
    }


    @PostMapping("/authorization")
    public TokenResponse customerAuthorization(@RequestParam String userName, @RequestParam String password) {
        TokenResponse tokenResponse = null;
        try {
            tokenResponse = customerService.customerAuthorization(userName, password);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return tokenResponse;
    }

    @PostMapping("/refreshToken")
    public TokenResponse customerTokenResposnseRefresh(@RequestParam String userName, @RequestParam String refreshToken) {
        TokenResponse tokenResponseRefresh = null;
        try {
            tokenResponseRefresh = customerService.customerRefreshToken(userName, refreshToken);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage() + "/api/customer/refreshToken");
        }
        return tokenResponseRefresh;
    }

    @GetMapping("/token")
    public String getTestToken() {
        return "You secret token";
    }

}
