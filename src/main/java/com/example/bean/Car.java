package com.example.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String number;

    public String getNumber() {
        return "粤B8897D";
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
