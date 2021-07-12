package com.hp.entity;

public class Customer {
    private String account,cname,password,phone;
    private double money;

    public String getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "account='" + account + '\'' +
                ", cname='" + cname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                '}';
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
