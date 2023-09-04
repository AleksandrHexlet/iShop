package com.edu.ishop.controller;

import com.edu.ishop.entity.FeedBack;
import com.edu.ishop.entity.Product;
import com.edu.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Product> getProductsByCategoryName(@RequestParam String name,@RequestParam(required = false) String image) {
        return productService.getProductsByParams(name, image);
    }

    @GetMapping("/by")
    public List<Product> getProductsByNames(@RequestParam String names){
        String[] nameArr = names.split("-");
        return productService.getProductListByName(nameArr);
    }
    @PostMapping("/feedback")
    public FeedBack getProductsByNames(@RequestParam int idProduct, @RequestParam  String textFeedback){
    FeedBack feedBack = productService.addFeedBackToProduct(idProduct,textFeedback);
    return feedBack;
    };
}
///product?name=bread&image=breadImg