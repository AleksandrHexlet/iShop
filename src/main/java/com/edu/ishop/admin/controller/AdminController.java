package com.edu.ishop.admin.controller;

import com.edu.ishop.admin.services.AdminService;
import com.edu.ishop.helpers.entity.Category;
import com.edu.ishop.helpers.entity.Customer;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
