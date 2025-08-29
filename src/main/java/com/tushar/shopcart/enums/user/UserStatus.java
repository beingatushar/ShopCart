package com.tushar.shopcart.enums.user;

import jakarta.persistence.Table;

@Table(name = "user-roles")
public enum UserStatus {
    ACTIVE, INACTIVE, SUSPENDED, DELETED
}