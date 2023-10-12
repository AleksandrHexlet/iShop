package com.edu.ishop.admin.controller;

import com.edu.ishop.admin.services.AdminService;
import com.edu.ishop.helpers.entity.Category;
import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.ProductManufacturer;
import com.edu.ishop.helpers.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminServiceOUT) {
        this.adminService = adminServiceOUT;
    }

    ;

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        try {
            return adminService.createNewProduct(product);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {

        try {
            return adminService.createNewCustomer(customer);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/category")
    public Category createNewCategory(@RequestBody Category category) {
        try {
            return adminService.createNewCategory(category);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/trader")
    public ProductManufacturer createNewTrader(@RequestBody ProductManufacturer productManufacturer){
            return adminService.createNewTrader(productManufacturer);

    }

    @PutMapping("/trader")
    public ResponseEntity<String> updateTrader(@RequestParam String userName,
                                       @RequestParam(required = false, defaultValue = "-1") int rate,
                                       @RequestParam(required = false) String cityStorage ){
        System.out.println("rate = " + rate);

        try {
            adminService.updateTrader(userName, rate, cityStorage);
            return new ResponseEntity<String> ("update success", HttpStatusCode.valueOf(204));
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
        }
    }

}
