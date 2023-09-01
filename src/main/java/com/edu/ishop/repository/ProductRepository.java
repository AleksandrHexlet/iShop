package com.edu.ishop.repository;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryProductUrl(String url);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name_product = :name and (url_image = :image or " +
            "url_image = null)")
    List<Product> getProductsByNameAndImage(String name, String image);


    @Query(nativeQuery = true, value = "SELECT  * FROM product WHERE name_product in (:nameArray)")
    List<Product> getProductsByName(@Param(value= "nameArray") String[] array);
}

//SELECT * FROM product WHERE name_product in (:nameArray)