package com.edu.ishop.helpers.specification;

import com.edu.ishop.helpers.entity.Category;
import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.ProductManufactureId;
import com.edu.ishop.helpers.entity.ProductManufacturer;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
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
            Join<ProductManufacturer, Product> productManufacturerProductJoin = root.join("productManufacturer",
                    JoinType.LEFT);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(productManufacturerProductJoin.get("name"), id.getName()),
                    criteriaBuilder.equal(productManufacturerProductJoin.get("country"), id.getCityStorage()),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("quantityStock"), quantity),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating)


            );
        };

    }

//    товары: рейтинг выше указанного, название категории равно указанной,
//    страна производителя (одна или несколько из указанных)

    public static Specification<Product> findProductByCategoryAndCountryAndRating(
            double rating, String nameCategory, List<String> manufacturerCountries
    ) {
        return (root, query, criteriaBuilder) -> {
            Join<Category, Product> categoryProductJoin = root.join("categoryProduct", JoinType.LEFT);
            Join<ProductManufacturer, Product> ProductManufacturerCategoryJoin =
                    root.join("productManufacturer", JoinType.LEFT);
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("rating"), rating),
                    criteriaBuilder.equal(categoryProductJoin.get("name"), nameCategory),
                    ProductManufacturerCategoryJoin.get("country").in(manufacturerCountries)
            );
///
        };
    }

    public static Specification<Product> findProductbyCategoryAndRatingAndDate(int categoryId, double rating, LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            Join<Category, Product> categoryProductJoin = root.join("categoryProduct", JoinType.LEFT);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(categoryProductJoin.get("id"), categoryId),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating),
                    criteriaBuilder.lessThanOrEqualTo(root.get("dateAdded"), date)

            );
        };
    }


    public static Specification<Product> findProductActiveManufactureAndCountUpZero() {
        return (root, query, criteriaBuilder) -> {
            Join<ProductManufacturer, Product> ProductProductManufacturerJoin =
                    root.join("productManufacturer", JoinType.LEFT);
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThan(root.get("quantityStock"), 0),
                    criteriaBuilder.isTrue(ProductProductManufacturerJoin.get("isActive"))
            );
        };
    }

    public static Specification<Product>findProductByName(String name){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nameProduct")),"%"+name.toLowerCase()+"%");
        };
    }
     public static  Specification<Product>findByCategoryAndQuantityAndRatingAndManufacturerName(
             String categoryURL, Integer quantity, double rating,List<String> namesManufacturer
     ){
       return (root, query, criteriaBuilder) -> {
           List<Predicate> predicates = new ArrayList<>();
        Join<Category,Product> productCategoryJoin = root.join("categoryProduct",JoinType.LEFT);
        if(namesManufacturer != null && namesManufacturer.size()>0){
            Join<ProductManufacturer,Product>productProductManufacturerJoin = root.join("productManufacturer",JoinType.LEFT);
            predicates.add(criteriaBuilder.lower(productProductManufacturerJoin.get("name"))
                            .in(namesManufacturer));
//                            .in(namesManufacturer.stream().map((item)-> item.toLowerCase())));
//
//                            .in(namesManufacturer.forEach((item)-> item.toLowerCase())));
        }

        predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(productCategoryJoin.get("url")),categoryURL.toLowerCase()));
        if(quantity != null && quantity > 0){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("quantityStock"),quantity));
        }

        if(rating >= 0){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("rating"),rating));
        }


           return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
       };
     }
}
//    private Category categoryProduct;
//    productManufacturer


//

//    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name_product = :name and (url_image = :image or " +
//            "url_image = null)")
//    List<Product> getProductsByNameAndImage(String name, String image);