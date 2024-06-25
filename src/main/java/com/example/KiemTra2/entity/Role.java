package com.example.KiemTra2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
    @Column
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) { // Add this method
        this.name = name;
    }

}
