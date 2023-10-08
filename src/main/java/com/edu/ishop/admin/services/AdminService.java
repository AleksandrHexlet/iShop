package com.edu.ishop.admin.services;

import com.edu.ishop.helpers.entity.*;
import com.edu.ishop.helpers.repository.CategoryRepository;
import com.edu.ishop.helpers.repository.CustomerRepositoryAdmin;
import com.edu.ishop.helpers.repository.ProductRepositoryAdmin;
import com.edu.ishop.helpers.exceptions.ResponseException;
import com.edu.ishop.helpers.repository.ProductManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class AdminService {
    ProductRepositoryAdmin productRepositoryAdmin;
    ProductManufactureRepository productManufactureRepository;
    CategoryRepository categoryRepository;
    CustomerRepositoryAdmin customerRepositoryAdmin;

    @Autowired
    public AdminService(ProductRepositoryAdmin productRepositoryAdmin, ProductManufactureRepository productManufactureRepository, CategoryRepository categoryRepository, CustomerRepositoryAdmin customerRepositoryAdmin) {
        this.productRepositoryAdmin = productRepositoryAdmin;
        this.productManufactureRepository = productManufactureRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepositoryAdmin = customerRepositoryAdmin;
    }









    public Product createNewProduct(Product product) throws ResponseException {
        product.setDateAdded(LocalDate.now());
        ProductManufacturer productManufacturer = productManufactureRepository
                .findById(new ProductManufactureId(product.getProductManufacturer()
                .getName(), product.getProductManufacturer().getCity())).orElse(null);
        if (productManufacturer == null || !productManufacturer.isActive()) {
            throw new ResponseException("Товар не может быть добавлен. Производитель " +
                    "не существует ил не активен");
        }
        Product prod = productRepositoryAdmin.save(product);

        return prod;
    }

    public Customer createNewCustomer(Customer customer) throws ResponseException {
    Customer customerIsExist = customerRepositoryAdmin
            .getCustomersByUserName(customer.getUserName()).get();
    if(customerIsExist != null) {
        throw new ResponseException("Клиент с таким userName уже существует ");
    }
        return customerRepositoryAdmin.save(customer);


    }

    public Category createNewCategory (Category category)throws ResponseException {
     Category сategoryIsExist = categoryRepository.findByName(category.getName());
     if (сategoryIsExist != null)   {
         throw new ResponseException("Категория с таким именем уже существует ");
     }
        return categoryRepository.save(category);
    }



}


