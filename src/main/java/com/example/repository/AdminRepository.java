package com.example.repository;

import com.example.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminRepository extends CrudRepository<Admin, Long>, JpaRepository<Admin, Long>, QueryDslPredicateExecutor<Admin> {
    public Admin findByEmail(String email);
}
