package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.ProductCustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCustomerOrderRepository  extends JpaRepository<ProductCustomerOrder, Integer> {

    List<ProductCustomerOrder> findByProductTraderUserName(String userName);
    // existS не exist
    boolean existsByProductIdAndProductTraderUserName(int productId, String userName);
}
