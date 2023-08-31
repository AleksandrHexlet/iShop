package com.edu.ishop.repository;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Category findByName(String name);
    Category findByUrl(String url_image);


// 6. Реализовать возможность обработки запроса на получение товара по его уникальному идентификатору
@Query(nativeQuery = true, value = "SELECT * FROM product WHERE product.id = :id")
List<Category> findByCategory(String id);
}