package com.edu.ishop.controller;

import com.edu.ishop.entity.FeedBack;
import com.edu.ishop.entity.Product;
import com.edu.ishop.entity.ProductManufactureId;
import com.edu.ishop.exceptions.ResponseException;
import com.edu.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Product> getProductsByCategoryName(@PathVariable String urlCategory) {
        return productService.getProductList(urlCategory);
    }

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
    public FeedBack getProductsByNames(@RequestParam int idProduct, @RequestParam String textFeedback) {
        FeedBack feedBack = productService.addFeedBackToProduct(idProduct, textFeedback);
        return feedBack;
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
}


///product?name=bread&image=breadImg