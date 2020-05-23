package com.example.bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
    private String name;
    private int age;
    private String sex;
    private MobilePhone mobilePhone;
    private List<Integer> intlist;
    private List<MobilePhone> mobilePhoneList;
    private Set<MobilePhone> mobilePhoneSet;
    private Map<Integer, MobilePhone> mobilePhoneMap;
    private LocalDateTime createDateTime;
    private LocalDateTime lastUpdateDateTime;

    //默认构造方法
    public User() {
    }

    // 有参构造方法
    public User(String name, MobilePhone mobilePhone, List<MobilePhone> mobilePhoneList) {
        super();
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.mobilePhoneList = mobilePhoneList;
    }

    public List<Integer> getIntlist() {
        return intlist;
    }

    public void setIntlist(List<Integer> intlist) {
        this.intlist = intlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<MobilePhone> getMobilePhoneList() {
        return mobilePhoneList;
    }

    public void setMobilePhoneList(List<MobilePhone> mobilePhoneList) {
        this.mobilePhoneList = mobilePhoneList;
    }

    public Set<MobilePhone> getMobilePhoneSet() {
        return mobilePhoneSet;
    }

    public void setMobilePhoneSet(Set<MobilePhone> mobilePhoneSet) {
        this.mobilePhoneSet = mobilePhoneSet;
    }

    public Map<Integer, MobilePhone> getMobilePhoneMap() {
        return mobilePhoneMap;
    }

    public void setMobilePhoneMap(Map<Integer, MobilePhone> mobilePhoneMap) {
        this.mobilePhoneMap = mobilePhoneMap;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", sex=" + sex + ", mobilePhone=" + mobilePhone + ", mobilePhoneList=" + mobilePhoneList
                + ", phoneSet=" + mobilePhoneSet + ", mobilePhoneMap=" + mobilePhoneMap + "]";
    }

    public void init() {
        System.out.println("User->init->开始调用!");
    }

    public void destroy() {
        System.out.println("User->destroy->结束调用!");
    }

}