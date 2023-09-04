package com.edu.ishop.services;

import com.edu.ishop.entity.*;
import com.edu.ishop.repository.CategoryRepository;
import com.edu.ishop.repository.FeedBackRepository;
import com.edu.ishop.repository.ProductManufactureRepository;
import com.edu.ishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.edu.ishop.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    ProductManufactureRepository productManufactureRepository;
    CategoryService categoryService;
    List<Product> productList = new ArrayList<>();
    ProductManufacturer productManufacturer;
    FeedBackRepository feedBackRepository;
    FeedBack feedBack;

    @Autowired
    public ProductService(ProductManufactureRepository productManufactureRepository, FeedBackRepository feedBackRepository, ProductRepository productRepository, CategoryService categoryService, List<Product> productList,  FeedBack feedBack) {
        this.productRepository = productRepository;
        this.productManufactureRepository = productManufactureRepository;
        this.feedBackRepository = feedBackRepository;
        this.categoryService = categoryService;
        this.productList = productList;
        this.productManufacturer = productManufacturer;
        this.feedBack = feedBack;
    }


    //            this.nameProduct = nameProduct;
//        this.urlImage = urlImage;
//        this.price = price;
//        this.rating = rating;
//        this.quantityStock = quantityStock;
//        this.dateAdded = dateAdded;
//        this.productManufacturer = productManufacturer;
//        this.feedBack = feedBack;
//        this.categoryProduct = categoryProduct;
    public List<Product> getProduct() {

        LocalDate date2010 = LocalDate.of(2010, 12, 31);
        LocalDate date2011 = LocalDate.of(2011, 12, 31);
        LocalDate date2012 = LocalDate.of(2012, 12, 31);
        LocalDate date2013 = LocalDate.of(2013, 12, 31);
        LocalDate date2014 = LocalDate.of(2014, 12, 31);
        LocalDate date2015 = LocalDate.of(2015, 12, 31);
        ZonedDateTime zonedDT11 = ZonedDateTime.parse("2010-10-10T18:30:45+01:00[Europe/London]");
        ZonedDateTime zonedDT12 = ZonedDateTime.parse("2011-04-22T08:40:15+10:00[Australia/Sydney]", DateTimeFormatter.ISO_DATE_TIME);

        ProductManufacturer productManufacturer1 = new ProductManufacturer( "MilkCorp", "Russia", true);
        productManufactureRepository.save(productManufacturer1);
        FeedBack feedBack1 = new FeedBack("Отличный, вкусный продукт", null);

        Product milk = new Product("milk", "http://milk.com", new BigDecimal("234.56"), (short) 4.5, 99_999, date2010, productManufacturer1, categoryService.dairyProducts);

        Product hamburger = new Product("hamburger", "http://hamburger.com", new BigDecimal("345.56"), (short) 4.0, 95, date2011, productManufacturer1, categoryService.readyMadeFood);


        Product washingMachine = new Product("washingMachine", "http://washingMachine.com", new BigDecimal("11234.56"), (short) 4.2, 99_999, date2012, productManufacturer1, categoryService.homeAppliances);
        Product Iphone19 = new Product("Iphone19", "http://Iphone19.com", new BigDecimal("5561.56"), (short) 3.5, 12399_999, date2013, productManufacturer1, categoryService.electronic);
        Product orange = new Product("orange", "http://orange.com", new BigDecimal("34.60"), (short) 4.1, 2_999, date2014, productManufacturer1, categoryService.healthyFood);


        Product carrot = new Product("carrot", "http://carrot.com", new BigDecimal("24.57"), (short) 4.9, 67_999, date2015, productManufacturer1, categoryService.healthyFood);
        Product sportsShoes = new Product("sportsShoes", "http://sportsShoes.com", new BigDecimal("4234.56"), (short) 4.2, 78_999, date2010, productManufacturer1, categoryService.sport);
        Product sportsTrousers = new Product("sportsTrousers", "http://sportsTrousers.com", new BigDecimal("7345.77"), (short) 4.3, 89_999, date2010, productManufacturer1, categoryService.sport);


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

    public List<Product> getProductListByName(String[] nameArr) {
        return productRepository.getProductsByName(nameArr);
    }

    public FeedBack addFeedBackToProduct(int id, String textFeedBack) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;
        FeedBack feedBack = new FeedBack(textFeedBack, product);
        product.setFeedbackToList(feedBack);
        feedBackRepository.save(feedBack);
        return feedBack;
    }

    ;

    @Bean
    public CommandLineRunner createTableProduct() {
        return (args) -> {
            productRepository.saveAll(getProduct());

        };
    }
}
