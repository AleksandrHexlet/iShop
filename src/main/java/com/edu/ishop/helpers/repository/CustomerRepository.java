package com.edu.ishop.helpers.repository;


import com.edu.ishop.helpers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
