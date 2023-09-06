package com.edu.ishop.specification;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.Product;
import com.edu.ishop.entity.ProductManufactureId;
import com.edu.ishop.entity.ProductManufacturer;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//private String nameProduct;
//
//@Column(nullable = false)
//private String urlImage;

public class ProductSpecifications {
    public static Specification<Product> getProductsByNameAndImage(String name, String image) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("nameProduct"), name),
                    criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("urlImage"), image),
                            criteriaBuilder.isNull(root.get("urlImage"))
                    )
            );
        });
    }

//    @Query("SELECT prod FROM Product prod LEFT JOIN prod.productManufacturer manufactory " +
//            "WHERE manufactory.name =:#{#id.name} AND manufactory.country =:#{#id.country} " +
//            "AND prod.quantityStock >= :quantity AND prod.rating >=:rating")
//    List<Product>findProductByProductManufacturerAndQuantityStockAndRating(ProductManufactureId id, int quantity, double rating);

    // вместо имени и страны хотел передать ProductManufactureId и у него взять имя и страну.Idea писала ошибку.
    // Как можно использовать ProductManufactureId в аргументах ?


    public static Specification<Product> findProductByProductManufacturerAndQuantityStockAndRating(ProductManufactureId id,
                                                                                                   int quantity,
                                                                                                   double rating) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductManufacturer,Product> productManufacturerProductJoin = root.join("productManufacturer",
                    JoinType.LEFT);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(productManufacturerProductJoin.get("name"),id.getName()),
                    criteriaBuilder.equal(productManufacturerProductJoin.get("country"),id.getCountry()),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("quantityStock"),quantity),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("rating"),rating)


            );
        };

    }

//    товары: рейтинг выше указанного, название категории равно указанной,
//    страна производителя (одна или несколько из указанных)

    public static Specification<Category> findProductByCategoryAndCountryAndRating(
            double rating,String nameCategory,String[] manufacturerCountries
    ){
        return (root, query, criteriaBuilder) -> {
            Join<Category,Product>categoryProductJoin = root.join("categoryProduct",JoinType.LEFT);
            Join<ProductManufacturer,Product>ProductManufacturerCategoryJoin =
                    categoryProductJoin.join("productManufacturer",JoinType.LEFT);
           return criteriaBuilder.and(
               criteriaBuilder.equal(root.get("rating"),rating),
               criteriaBuilder.equal(categoryProductJoin.get("name"),nameCategory),
               criteriaBuilder.in(ProductManufacturerCategoryJoin.get("country"),manufacturerCountries)
           );
///
        };
    }


}


//    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name_product = :name and (url_image = :image or " +
//            "url_image = null)")
//    List<Product> getProductsByNameAndImage(String name, String image);