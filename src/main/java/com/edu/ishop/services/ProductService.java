package com.edu.ishop.services;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.Product;
import com.edu.ishop.repository.CategoryRepository;
import com.edu.ishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.edu.ishop.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    CategoryService categoryService;
    List<Product> productList = new ArrayList<>();
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    Product milk = new Product("milk", "meal", "http://milk.com", categoryService.dairyProducts);
    Product hamburger = new Product("hamburger", "hamburger", "http://hamburger.com", categoryService.readyMadeFood);

    Product washingMachine = new Product("washingMachine", "washingMachine", "http://washingMachine.com", categoryService.homeAppliances);
    Product Iphone19 = new Product("Iphone19", "Iphone19", "http://Iphone19.com", categoryService.electronic);

    Product orange = new Product("orange", "orange", "http://orange.com", categoryService.healthyFood);
    Product carrot = new Product("carrot", "carrot", "http://carrot.com", categoryService.healthyFood);

    Product sportsShoes = new Product("sportsShoes", "sportsShoes", "http://sportsShoes.com", categoryService.sport);

    Product sportsTrousers = new Product("sportsTrousers", "sportsTrousers", "http://sportsTrousers.com", categoryService.sport);


    public List<Product> getProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(milk);
        productList.add(hamburger);
        productList.add(washingMachine);
        productList.add(Iphone19);
        productList.add(orange);
        productList.add(carrot);
        productList.add(sportsShoes);
        productList.add(sportsTrousers);
        return productList;
    }


@Bean
    public CommandLineRunner createTableProduct() {
        return (args) -> {
            productRepository.saveAll(getProduct());

        };
    }
}
