package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryAdmin  extends CrudRepository<Product, Integer> {
}
