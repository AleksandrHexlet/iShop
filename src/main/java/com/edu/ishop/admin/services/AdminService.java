package com.edu.ishop.admin.services;

import com.edu.ishop.admin.repository.ProductRepository;
import com.edu.ishop.entity.Product;
import com.edu.ishop.entity.ProductManufactureId;
import com.edu.ishop.entity.ProductManufacturer;
import com.edu.ishop.exceptions.ResponseException;
import com.edu.ishop.repository.ProductManufactureRepository;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service

public class AdminService {
    ProductRepository productRepository;
    ProductManufactureRepository productManufactureRepository;

    @Autowired
    public AdminService(ProductRepository productRepository, ProductManufactureRepository productManufactureRepository) {
        this.productRepository = productRepository;
        this.productManufactureRepository = productManufactureRepository;
    }

    public Product createNewProduct(Product product) throws ResponseException {
        product.setDateAdded(LocalDate.now());
        ProductManufacturer productManufacturer = productManufactureRepository.findById(new ProductManufactureId(product.getProductManufacturer()
                .getName(), product.getProductManufacturer().getCity())).orElse(null);
        if (productManufacturer == null || !productManufacturer.isActive()) {
            throw new ResponseException("Товар не может быть добавлен. Производитель " +
                    "не существует ил не активен");
        }
        Product prod = productRepository.save(product);

        return prod;
    }
}


