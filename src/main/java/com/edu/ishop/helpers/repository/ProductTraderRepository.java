package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.ProductTrader;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductTraderRepository extends CrudRepository<ProductTrader, Integer>,LoginDataRepository<ProductTrader> {
    ProductTrader findByName(String productTraderName);

}
