package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.ProductManufactureId;
import com.edu.ishop.helpers.entity.ProductManufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ProductManufactureRepository extends CrudRepository<ProductManufacturer, ProductManufactureId> {

}
