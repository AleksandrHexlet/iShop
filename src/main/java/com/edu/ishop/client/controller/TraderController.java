package com.edu.ishop.client.controller;

import com.edu.ishop.client.services.TraderService;
import com.edu.ishop.helpers.entity.ProductTrader;
import com.edu.ishop.helpers.exceptions.ResponseException;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trader")
public class TraderController {
   private TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> traderRegistration(@Valid @RequestBody ProductTrader productTrader){
        System.out.println("productTrader === " + productTrader);
        try{
            traderService.traderRegistration(productTrader);
            return ResponseEntity.ok("Продавец успешно зарегистрирован");
        } catch(ResponseException exception){
           return ResponseEntity.badRequest().body(exception.getMessage()
                    + "Неверные данные для регистрации продавца");
        }

    }

}
