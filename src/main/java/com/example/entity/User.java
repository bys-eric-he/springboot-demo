package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseModel {

    @NotNull(message = "{username.empty}")
    private String username;

    @NotNull(message = "{content.empty}")
    private String content;

    public void update(User user) {
        this.username = user.username;
        this.content = user.content;
    }

    @OneToMany(targetEntity = Address.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @OneToMany(targetEntity = EmailAddress.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<EmailAddress> emailAddresses;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_product_mapping")
    private Set<Product> products;

    /**
     * OneToOne中如果 optional = false 表示IDCard表生成的user_id外键不允许为空。
     * 同时在User对象中如果@OneToOne(optional = false)则表示,删除User对象时，不删除IDCard对象
     * JPA会将IDCard对象中的User对象也是IDCard表中的user_id字段置为NULL
     * 但此时如果IDCard中的user_id如果不允许为空时，则会报错。
     */
    @OneToOne(targetEntity = IDCard.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private IDCard idCard;

    /**
     * 使用Set存储Object对象，重写equals和hashCode方法
     * 要使用Set存储Object的对象，执行add方法的时候，会先判断equals方法是否与已有的值相等，
     * 再判断是否有相同的hash值，若这两个方法结果都为true，则这个对象就认为已经存在，不插入，
     * 所以，我们要对equals和hashCode方法进行重写。
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            System.out.println("true");
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            System.out.println("false");
            return false;
        }
        return super.getId().equals(((User) obj).getId()) && username.equals(((User) obj).username);
    }

    /**
     * 使用Set存储Object对象，重写equals和hashCode方法
     * 要使用Set存储Object的对象，执行add方法的时候，会先判断equals方法是否与已有的值相等，
     * 再判断是否有相同的hash值，若这两个方法结果都为true，则这个对象就认为已经存在，不插入，
     * 所以，我们要对equals和hashCode方法进行重写。
     *
     * @return
     */
    @Override
    public int hashCode() {
        int flag = 36;
        int idNum = super.getId() != null ? Integer.valueOf(super.getId().toString()) : (int) (1 + Math.random() * 100);
        return flag * idNum;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
}