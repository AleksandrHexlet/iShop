package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.Product;
import com.edu.ishop.helpers.entity.ShopingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

import java.util.List;


public interface ShopingListRepository extends JpaRepository<ShopingList, Integer> {


    List<Product> findByPriceLessThanAndNameProductIn(BigDecimal budget, String nameThing);

}
