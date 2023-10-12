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
                .findById(product.getProductManufacturer().getUserName()).orElse(null);
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
        if (customerIsExist != null) {
            throw new ResponseException("Клиент с таким userName уже существует ");
        }
        return customerRepositoryAdmin.save(customer);


    }

    public Category createNewCategory(Category category) throws ResponseException {
        Category сategoryIsExist = categoryRepository.findByName(category.getName());
        if (сategoryIsExist != null) {
            throw new ResponseException("Категория с таким именем уже существует ");
        }
        return categoryRepository.save(category);
    }


    public ProductManufacturer createNewTrader(ProductManufacturer productManufacturer) {
        productManufacturer.setDateRegistration(LocalDate.now());
        productManufactureRepository.save(productManufacturer);
        return productManufacturer;
    }

    public void updateTrader(String userName, int rate, String cityStorage) throws ResponseException {
        if (userName == null)
            throw new ResponseException("userName должен быть заполнен");
        if ((cityStorage != null && cityStorage.isEmpty() && rate == -1) || (cityStorage == null && rate == -1))
            throw new ResponseException("Нужна информация для обновления. Сейчас данные не переданы");
       ProductManufacturer productManufacturer =  productManufactureRepository.findById(userName).orElse(null);
       if(productManufacturer == null) throw new ResponseException("Продавец не зарегистрирован");
        if(productManufacturer.getCityStorage().equals(cityStorage) && productManufacturer.getRate() == rate)
            throw new ResponseException("Данные не требуют обновления.Они идентичны сохранённым в БД");
        if(rate != -1)productManufacturer.setRate(rate);
        if(cityStorage != null)productManufacturer.setCityStorage(cityStorage);
       productManufactureRepository.save(productManufacturer);
    }
}


