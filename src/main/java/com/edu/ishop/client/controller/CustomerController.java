package com.edu.ishop.client.controller;

import com.edu.ishop.client.services.CustomerService;
import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.exceptions.ResponseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    ;


    @PostMapping("/authorization")
    public String customerAuthorization(@RequestParam String userName, @RequestParam String password) {
        String token = customerService.customerAuthorization(userName, password);
        return token;
    }

    @GetMapping("/token")
    public String getTestToken() {
        return "You secret token";
    }

}
