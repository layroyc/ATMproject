package com.hp.entity;

import java.util.ArrayList;
//使用单例模式，把 客户 初始化了
public class CustomerData {
    private static CustomerData ourInstance = new CustomerData();
    //提取为成员变量
    private ArrayList<Customer> customerList;
    public static CustomerData getInstance() {
        return ourInstance;
    }

    private CustomerData() {
        //在构造方法中书写 初始化数据
        customerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //每循环一次，诞生一个customer对象，存入到集合中
            Customer customer = new Customer();
            customer.setAccount("52013146898"+i);
            customer.setPassword("123");
            customer.setCname("小鬼"+i+"号");
            customer.setMoney(10000);
            customer.setPhone("1591475369"+i);
            customerList.add(customer);
        }
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }
}
