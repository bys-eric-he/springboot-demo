package com.example.service.impl;

import com.example.entity.Product;
import com.example.repository.ProductRespository;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Override
    public Product insertProduct(Product product) {
        return productRespository.save(product);
    }

    @Override
    public Set<Product> insertProduct(Set<Product> products) {
        productRespository.save(products);
        return products;
    }

    @Override
    public Product selectProduct(Long id) {
        return productRespository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long id) throws Exception {
        productRespository.delete(id);
    }
}
