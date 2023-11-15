package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.ProductTrader;
import org.springframework.data.repository.CrudRepository;

public interface ProductTraderRepository extends CrudRepository<ProductTrader, String> {
   boolean existsByUserName(String userName);

    ProductTrader findByName(String productTraderName);
}
