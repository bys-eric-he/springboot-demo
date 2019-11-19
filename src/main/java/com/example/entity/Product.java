package com.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends BaseModel {
    private String name;
    private BigDecimal price;
    @ManyToMany(targetEntity = User.class, mappedBy = "products", cascade = CascadeType.ALL)
    private Set<User> users;

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
        return super.getId() != null
                ? super.getId().equals(((Product) obj).getId())
                : name.equals(((Product) obj).name);
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
