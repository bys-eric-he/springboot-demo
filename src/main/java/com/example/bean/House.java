package com.example.bean;

import org.springframework.stereotype.Component;

@Component
public class House {

    private String area;

    public String getArea() {
        return "300㎡";
    }

    public void setArea(String area) {
        this.area = area;
    }
}
