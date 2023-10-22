package com.edu.ishop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/adminHTML")
public class AdminControllerHTML {

   @GetMapping("/login")
    public String getLoginForm(@RequestParam(required = false) String failed){
        return "AdminLoginFormHTML";
    }
    @GetMapping("/account")
    public String getAccount(Principal admin){
        System.out.println("admin == "+ admin.getName());
        return "AdminAccountFormHTML";
    }



}
