package com.edu.ishop.repository;

import com.edu.ishop.entity.ProductManufactureId;
import com.edu.ishop.entity.ProductManufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ProductManufactureRepository extends CrudRepository<ProductManufacturer, ProductManufactureId> {

}
