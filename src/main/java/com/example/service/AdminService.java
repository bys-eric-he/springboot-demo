package com.example.service;

import com.example.entity.Admin;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface AdminService {
    Admin findByEmail(String email);

    Admin findOne(Long id);

    List<Admin> findList(Predicate predicate);

    void insertAdmin(Admin admin);
}
