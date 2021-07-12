package com.hp.service;

import com.hp.entity.Customer;
import com.hp.entity.CustomerData;
import com.hp.util.ATMDemo;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    //成员变量
    private ArrayList<Customer> customerList;
    private Customer customerNow;
    private Scanner scanner;

    //1、登录  判断账户密码是否正确
    public void checkPwd(String cardId, String password) {
        //对数据初始化
        CustomerData customerData = CustomerData.getInstance();
        customerList = customerData.getCustomerList();
        //1)拿到 cardId中的账户名 和  password 对 CustomerList中的数据进行对比
        System.out.println("cardId = " + cardId);
        System.out.println("password = " + password);

        for (Customer customer : customerList) {
            if (customer.getAccount().equals(cardId) && customer.getPassword().equals(password)) {
                //拿出这个用户
                customerNow = customer;
                //账户密码正确
                System.out.println("欢迎" + customer.getCname() + "顾客登录！请注意您的安全");
                //一级选择界面
                ATMDemo.oneOption();
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();
                oneOption(option);
            }
        }
    }

    //选择
    private void oneOption(String option) {
        switch (option) {
            case "1":
                System.out.println("余额查询");
                //查询余额
                doSelectMoney();
                //当按下1时 回退到一级选择界面
                goOneHome();
                break;
            case "2":
                //System.out.println("取款");
                ATMDemo.withdrawalOpt();//取款页面
                System.out.println("\t");
                //取款
                doWithdrawal();
                goOneHome();
                break;
            case "3":
                System.out.println("转账");
                transferAccounts();
                goOneHome();
                break;
            case "4":
                //System.out.println("存款");
                //存款选择界面
                ATMDemo.depositOpt();
                deposit();//存款操作
                goOneHome();
                break;
            case "5":
                System.out.println("退卡");
                refundCard();
                break;
            default:
                System.out.println("对不起,您选择的功能有误!");
                break;

        }
    }
    //退卡
    private void refundCard() {
        System.out.println("您是否继续操作yes/no[Y/N]");
        scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if(s.equalsIgnoreCase("y")){//equalsIgnoreCase忽略大小写
            ATMDemo.welcome();
        }
        if(s.equalsIgnoreCase("n")){
            System.out.println("退卡成功");
        }
    }

    //转账操作
    private void transferAccounts() {
        System.out.println("请输入对方的账户号码");
        scanner = new Scanner(System.in);
        String otherAccount = scanner.nextLine();
        System.out.println("请输入您需要转账的金额：");
        String otherMoney = scanner.nextLine();
        //自己减钱
        Double otherMoneyDo = Double.parseDouble(otherMoney);
        double nowMoney = customerNow.getMoney()-otherMoneyDo;//自己被转账后的余额

        //别人加钱，根据 otherAccount 本人的账户查询出 别人的余额，查出别人的余额后，修改别人的余额
        //因为 所有人都在customerList中 ，遍历集合
        Customer other = null;
        for (Customer customer : customerList) {
            //如果    customer.getAccount 等于  otherAccount，那么就是这个人被选出来了
            if (customer.getAccount().equals(otherAccount)) {
                other = customer;
            }
        }
        double otherOneMoney = other.getMoney() + otherMoneyDo;//自己的的钱+被转账得钱

        //自己和别人 都更新一下   钱数
        customerNow.setMoney(nowMoney);
        other.setMoney(otherOneMoney);
    }

    //存款操作
    private void deposit() {
        //scanner 接收 钱数
        scanner = new Scanner(System.in);
        Integer i = scanner.nextInt();
        double money = customerNow.getMoney();//获取顾客当前的money
        if(i == 1){
            //存款100 让 顾客的 钱+100
            money = money+100;
            customerNow.setMoney(money);
            System.out.println("您的余额为 " + money + "元");

        }else if(i==2) {
            //存款100 让 顾客的 钱+200
            money = money + 200;
            customerNow.setMoney(money);
            System.out.println("您的余额为 " + money + "元");
        }else if(i==3) {
            System.out.println("请存入大于800的金额");
            scanner = new Scanner(System.in);
            double moneyIn = scanner.nextDouble();//接收到的金额
            //判断存款的金额是否是 大于800且是100的倍数, 不许是 浮点数,
            if (moneyIn > 800) {
                if (moneyIn % 100 == 0) {
                    double newMoney = customerNow.getMoney() + moneyIn;//当前存款过后的金额
                    //3、更新 当前用户的余额
                    customerNow.setMoney(newMoney);
                    System.out.println("存款成功!余额为" + newMoney);
                } else {
                    System.out.println("存入金额有误，请存放金额为100的倍数");
                }

            } else {
                System.out.println("请存入大于800的金额");
            }

        }
    }
    //取款操作
    private void doWithdrawal() {
        scanner = new Scanner(System.in);
        String numg = scanner.nextLine();
        //获取顾客当前的money
        double money = customerNow.getMoney();
        switch (numg) {
            case "1":
                //那么取款100 就应该 让 顾客的 钱-100
                money = money-100;
                if (100 < customerNow.getMoney()) {
                    System.out.println("请及时拿走现金 100元");
                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money + "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "2":
                money = money-200;
                if (200 < customerNow.getMoney()) {
                    System.out.println("请及时拿走现金 200元");

                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money+ "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "3":
                money = money-300;
                if (300 < customerNow.getMoney()) {
                    System.out.println("请及时拿走现金 300元");

                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money + "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "4":
                money = money-500;
                if (500 < customerNow.getMoney()) {

                    System.out.println("请及时拿走现金 500元");
                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money + "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "5":
                money = money-800;
                if (800 < customerNow.getMoney()) {

                    System.out.println("请及时拿走现金 800元");
                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money + "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "6":
                money = money-1000;
                if (1000 < customerNow.getMoney()) {

                    System.out.println("请及时拿走现金 1000元");
                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money + "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "7":
                money = money-2000;
                if (2000 < customerNow.getMoney()) {

                    System.out.println("请及时拿走现金 2000元");
                    System.out.println("*********************************");
                    System.out.println("您的余额为 " + money + "元");
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);

                } else {
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            case "8":
                System.out.println("其他");
                Scanner other = new Scanner(System.in);
                double v = other.nextDouble();
                money = money-v;
                if (v < customerNow.getMoney()) {
                    System.out.println("请及时拿走现金" + v + "元");

                    System.out.println("您的余额为" + money);
                    // 取完款项之后,  更新 原有的 存款
                    customerNow.setMoney(money);
                }else{
                    System.out.println("余额不足，请选择其他业务");
                }
                break;
            default:
                System.out.println("请输入正确的序号");
                break;
        }
    }

    //查询余额
    private void doSelectMoney() {
        double money = customerNow.getMoney();
        System.out.println("余额是" + money + "元");

    }

    //返回选择界面
    private void goOneHome() {
        ATMDemo.oneOption();
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        System.out.println("option = " + option);
        oneOption(option);//递归算法
    }
}
