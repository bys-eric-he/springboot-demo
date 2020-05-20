package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "email")
public class EmailAddress extends BaseModel {

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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
                ? super.getId().equals(((EmailAddress) obj).getId()) && address.equals(((EmailAddress) obj).address)
                : address.equals(((EmailAddress) obj).address);
    }

    @Override
    public String toString() {
        return "{E-Mail Address:" + this.getAddress() + "}";
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
