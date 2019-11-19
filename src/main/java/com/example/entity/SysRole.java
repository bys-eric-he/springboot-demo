package com.example.entity;

import javax.persistence.Entity;

@Entity
public class SysRole extends BaseModel{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
