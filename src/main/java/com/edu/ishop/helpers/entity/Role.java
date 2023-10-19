package com.edu.ishop.helpers.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue
    int id;
    @Enumerated(EnumType.STRING)
    RoleType roleType;



    public enum RoleType {
        ADMIN,
        CUSTOMER,
        TRADER,
        READONLY_ADMIN,
    }



}
