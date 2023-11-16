package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.LoginData;
import com.edu.ishop.helpers.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface LoginDataRepository <T extends LoginData> extends JpaRepository<T, Integer> {
    Optional<T> findByUserName(String userName);
    public boolean existsByUserName(String userName);

    public Optional<T> findByUserNameAndRoleRoleTypeIn(String userName, Collection<Role.RoleType> roleTypes);

}
