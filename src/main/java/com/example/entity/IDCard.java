package com.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by eric on 2018/3/8.
 */
@Entity
@Table(name = "IdCard")
public class IDCard extends BaseModel {
    private String cardNumber;
    private String cardAddress;

    /**
     * optional = false 表示IDCard表生成的user_id外键不允许为空。
     * 同时在User对象中如果@OneToOne(optional = false)则表示,删除User对象时，不删除IDCard对象
     * JPA会将IDCard对象中的User对象也是IDCard表中的user_id字段置为NULL
     * 但此时如果IDCard中的user_id如果不允许为空时，则会报错。
     */
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
