package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository <Role, Integer> {

    public Role findByRoleType(Role.RoleType roleType);
}
