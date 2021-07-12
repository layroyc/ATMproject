package com.hp.util;
/*
 * 文本信息的工具类
 * */
public class ATMDemo {
    //welcome界面
    /*
     * static 修饰方法，1.可以直接被类名.方法（）调用
     * 2、在内存中独一份，其他修改，他会跟着修改
     * 关于static 静态代码块， 他一般适用于 界面场景的提前加载，如 王者荣耀中 峡谷的轮廓永远优先于英雄出现
     *
     * static {
     *   //这是 静态代码块
     * }
     * */
    public static void welcome() {
        System.out.println("*****************************************");
        System.out.println("***************ATM取款机******************");
        System.out.println("***********欢迎您使用ATM取款机*************");
        System.out.println("*****************************************");
    }

    //客户的一级选择
    public static void oneOption(){
        System.out.println("*****************************************");
        System.out.println("********请输入你想要的操作类型：*********");
        System.out.println("********1.余额查询        2.取款*********");
        System.out.println("********3.转账            4.存款*********");
        System.out.println("********5.退卡                  *********");
        System.out.println("*****************************************");
    }

    //取款选择权
    public static void withdrawalOpt(){
        System.out.println("请输入取款金额:");
        System.out.println("*****************************************");
        System.out.println("********请输入你想要的操作类型：*********");
        System.out.println("********1.100       2.200*********");
        System.out.println("********3.300           4.500*********");
        System.out.println("********5.800           6.1000*********");
        System.out.println("********7.2000          8.其他*********");
        System.out.println("*****************************************");
    }

    //存款选择权
    public static void depositOpt(){
        System.out.println("请选择要存款的金额:");
        System.out.println("*****************************************");
        System.out.println("********请输入你想要的操作类型：*********");
        System.out.println("********1.100       2.200*********");
        System.out.println("**************3.其他****************");
        System.out.println("*****************************************");
    }
}
