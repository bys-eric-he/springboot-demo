package com.example.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = -8054692082716173379L;
    private Long id = 0L;
    private String name;
    private BigDecimal price;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
