package com.edu.ishop.client.controller;

import com.edu.ishop.helpers.entity.FeedBack;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.client.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{urlCategory}")
    public List<Product> getProductsByCategoryName(@PathVariable String urlCategory,
                                                   @RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam(required = false) int quantity,
                                                   @RequestParam(required = false) double rating,
                                                   @RequestParam(required = false) String namesManufacturer,
                                                   @RequestParam(required = false) String orderSort) {


        List<String> namesManufacturerArr = new ArrayList<>();
        if(namesManufacturer.length() > 0){
             namesManufacturerArr = List.of(namesManufacturer.split(","));
        }

//        return productService.getProductList(urlCategory);
        return productService.getProductByCategoryAndQuantityAndRatingAndManufacturerName(urlCategory,
                quantity, rating,
                namesManufacturerArr,
                page, size, orderSort);

    }
////1. GET:: /product/category/{categoryUrl} - постраничное получение всех товаров определенной категории
//// с возможностью сортировки по возрастанию / убыванию цены или новизне (отзывы не нужны).
////        Номер страницы, количество записей и порядок сортировки передаются параметрами и
////        являются обязательными.Добавить фильтрацию по доступному количеству (больше или равно
////        указанному), по рейтингу (больше или равно указанному),
////        по производителю (один или несколько). Количество, рейтинг, производитель передаются
////        параметрами и не являются обязательными.


    @GetMapping
    public List<Product> getProductsByRandomCategoryAndRating() {
        return productService.getProductsByRandomCategoryAndRating();
    }

    @GetMapping("/by")
    public List<Product> getProductsByNames(@RequestParam String names) {
        String[] nameArr = names.split("-");
        return productService.getProductListByName(nameArr);
    }

    @PostMapping("/feedback")
    public ResponseEntity<FeedBack> getProductsByNames(@RequestParam int idProduct, @RequestParam String textFeedback) {

        FeedBack feedBack = productService.addFeedBackToProduct(idProduct, textFeedback);
        return new ResponseEntity<FeedBack>(feedBack, HttpStatus.CREATED);
    }

    ;

    @GetMapping("/id/{productId}")
    public Product getProductWithFeedbacksById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    ;


    @GetMapping("/filter")
    public List<Product> getProductByProductManufacturerAndQuantityStockAndRating(@RequestParam String country,
                                                                                  @RequestParam String name,
                                                                                  @RequestParam int quantity,
                                                                                  @RequestParam double rating) {

        try {
            return productService.getProductByProductManufacturerAndQuantityStockAndRating(country, name, quantity, rating);
        } catch (ResponseException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/name")
    public List<Product> getProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    ;



}

//getProductByCategoryAndQuantityAndRatingAndManufacturerName

///product?name=bread&image=breadImg