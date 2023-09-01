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

    public List<Product> getProduct() {
        Product milk = new Product("milk", "http://milk.com", categoryService.dairyProducts);
        Product hamburger = new Product("hamburger", "http://hamburger.com", categoryService.readyMadeFood);

        Product washingMachine = new Product("washingMachine", "http://washingMachine.com", categoryService.homeAppliances);
        Product Iphone19 = new Product("Iphone19", "http://Iphone19.com", categoryService.electronic);

        Product orange = new Product("orange", "http://orange.com", categoryService.healthyFood);
        Product carrot = new Product("carrot", "http://carrot.com", categoryService.healthyFood);

        Product sportsShoes = new Product("sportsShoes", "http://sportsShoes.com", categoryService.sport);

        Product sportsTrousers = new Product("sportsTrousers", "http://sportsTrousers.com", categoryService.sport);

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


    public List<Product> getProductList(String url) {
        return productRepository.findByCategoryProductUrl(url);
    }

    public List<Product> getProductsByParams(String name, String image) {

//        return productRepository.getProductsByNameAndImage(name,image);
        List<Product> productList = productRepository.getProductsByNameAndImage(name, image);
        System.out.println("productList = " + productList);
        return productList;
    }
     public List<Product> getProductListByName(String[] nameArr){
     return productRepository.getProductsByName(nameArr);
     }

    @Bean
    public CommandLineRunner createTableProduct() {
        return (args) -> {
            productRepository.saveAll(getProduct());

        };
    }
}
