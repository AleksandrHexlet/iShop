package com.edu.ishop.admin.controller;

import com.edu.ishop.admin.services.AdminService;
import com.edu.ishop.entity.Product;
import com.edu.ishop.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
            adminService.createNewProduct(product);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
