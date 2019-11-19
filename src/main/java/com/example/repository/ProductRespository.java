package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductRespository extends CrudRepository<Product, Long> {
}
