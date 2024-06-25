package com.example.KiemTra2.entity;

import jakarta.persistence.Column;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

public class UserRoleId implements Serializable {
    @Column(name = "user_id")
    private Integer user;

    @Column(name = "role_id")
    private Integer role;



}
