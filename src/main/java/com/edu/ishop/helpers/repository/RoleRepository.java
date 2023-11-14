package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository <Role, Integer> {

    public Optional<Role> findByRoleType(Role.RoleType roleType);
}
