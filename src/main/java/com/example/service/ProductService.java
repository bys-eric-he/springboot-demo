package com.example.service;

import com.example.entity.Product;

import java.util.Set;

/**
 * Created by eric on 2017/2/28.
 */
public interface ProductService {
    Product insertProduct(Product product);

    Set<Product> insertProduct(Set<Product> products);

    Product selectProduct(Long id);

    void deleteProduct(Long id) throws Exception;
}
