package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {
    public LoginData findByUserName(String userName);
    public boolean existsByUserName(String userName);

}
