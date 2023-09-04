package com.edu.ishop.repository;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.Product;
import com.edu.ishop.entity.ProductManufactureId;
import com.edu.ishop.entity.ProductManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryProductUrl(String url);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name_product = :name and (url_image = :image or " +
            "url_image = null)")
    List<Product> getProductsByNameAndImage(String name, String image);


//    @Query(nativeQuery = true, value = "SELECT  * FROM product WHERE name_product in (:nameArray)")
//    List<Product> getProductsByName(@Param(value= "nameArray") String[] array);


    @Query("SELECT prod FROM Product prod WHERE prod.quantityStock > :quantity AND prod.price < :price ")
    List<Product> getProductsByPriceAndQuantity(int quantity, BigDecimal price);

    @Query("SELECT product FROM Product product LEFT JOIN product.productManufacturer manufacture  WHERE " +
            "product.rating > :rating AND manufacture.country = :country")
    List<Product> getProductsByRatingAndManufactureCountry(short rating, String country);

 @Query("SELECT prod FROM Product prod WHERE prod.nameProduct in (:nameProduct)")
    List<Product> getProductsByName(@Param(value= "nameArray") String[] array);

 @Query("SELECT prod FROM Product prod LEFT JOIN prod.categoryProduct category WHERE " +
         "category.name = :nameCategory AND prod.price > :minPrice AND prod.price < :maxPrice")
 List<Product> getProductsByCategoryAndPrice(String nameCategory, BigDecimal minPrice, BigDecimal maxPrice);


    @Query("SELECT prod FROM Product prod WHERE prod.categoryProduct =:category  AND prod.rating >= :rating AND prod.dateAdded >= :weekAgo")
    List<Product> findProductsbyRatingAndAddedLastWeek(Category category, short rating, @Param("weekAgo") LocalDate weekAgo);
//    получение товаров XXX категории с рейтингом равным или выше ХХХ, добавленных за последние XXX дней


// получение товаров из XXX категории с выборкой по производителю (равно указанному),
// по доступному количеству (больше или равно указанному), по рейтингу (больше или равно указанному)

    @Query("SELECT prod FROM Product prod LEFT JOIN prod.productManufacturer manufactory " +
            "WHERE manufactory.name =:name AND manufactory.country =:country " +
            "AND prod.quantityStock >= :quontity AND prod.rating >=:rating")
    List<Product>findProductByProductManufacturerAndQuantityStockAndRating(String name, String country,int quontity,short rating);

    // вместо имени и страны хотел передать ProductManufactureId и у него взять имя и страну.Idea писала ошибку.
        // Как можно использовать ProductManufactureId в аргументах ?



}

//SELECT * FROM product WHERE name_product in (:nameArray)
