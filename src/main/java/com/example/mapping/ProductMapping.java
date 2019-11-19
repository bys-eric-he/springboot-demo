package com.example.mapping;


import com.example.dto.ProductDto;
import com.example.entity.Product;

public class ProductMapping {

    public static Product toEntity(ProductDto productDto) {
        Product product = null;
        if (productDto != null) {
            product = new Product();
            product.setId(productDto.getId());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
        }
        return product;
    }
}
