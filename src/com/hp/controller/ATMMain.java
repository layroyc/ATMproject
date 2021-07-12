package com.hp.controller;

import com.hp.entity.Customer;
import com.hp.entity.CustomerData;
import com.hp.entity.Manager;
import com.hp.entity.ManagerData;
import com.hp.service.CustomerService;
import com.hp.util.ATMDemo;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMMain {

    private static String cardId;
    private static String password;
    private static CustomerService customerService;

    public static void main(String[] args) {
        customerService = new CustomerService();
        //测试客户类的数据有没有存好
        CustomerData customerData = CustomerData.getInstance();
        ArrayList<Customer> customerList = customerData.getCustomerList();
        for (Customer customer : customerList) {
            System.out.println("customer = " + customer);
        }

        //测试管理员类的数据是否存储好
        ManagerData managerData = ManagerData.getInstance();
        ArrayList<Manager> managerList = managerData.getManagerList();
        for (Manager manager : managerList) {
            System.out.println("manager = " + manager);
        }
        //1、界面阶段
        ATMDemo.welcome();

        int i = 0;
        while (i<3){
            //2、输入账号密码 阶段   输入超过3次终止操作(跳出循环)
            doWriteUPassword();
            //3.其他操作-- 1）校验角色 2）判断账户密码的正确性
            doCheckPassword(cardId,password);
            i++;
        }
    }

    private static void doCheckPassword(String cardId,String password) {
        //①先校验角色，判断cardId的长度
        if(cardId.length()==12){//客户
            //校验密码
            customerService.checkPwd(cardId, password);

        }else{

        }

    }

    //输入账户名 和 密码
    private static void doWriteUPassword() {
        Scanner inputs = new Scanner(System.in);
        System.out.println("请输入卡号：");
        cardId = inputs.nextLine();
        System.out.println("cardId = " + cardId);

        System.out.println("请输入密码：");
        password = inputs.nextLine();
        System.out.println("password = " + password);
    }


}
