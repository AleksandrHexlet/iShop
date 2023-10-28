package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    public RefreshToken findByCustomerUserName(String userName);
}
