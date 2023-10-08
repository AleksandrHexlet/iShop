package com.edu.ishop.admin.repository;

import com.edu.ishop.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository  extends CrudRepository<Product, Integer> {
}
