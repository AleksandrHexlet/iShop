package com.edu.ishop.repository;

import com.edu.ishop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    Category findByUrl(String url);

    @Query(nativeQuery = true, value = "SELECT t2. * FROM category t1" + "LEFT JOIN category t2 "
            + "ON t1.id = t2.parent_id" + "WHERE t1.url =:url")
    List<Category> findSubCategories(String url);

    //5. Реализовать возможность обработки запроса на получение всех товаров определенной категории
 @Query(nativeQuery = true, value = "SELECT * FROM category WHERE category.url = :url")
    List<Category> findByCategory(String url);


}

