package com.hp.entity;

import java.util.ArrayList;
//管理员的初始化数据
public class ManagerData {
    private static ManagerData ourInstance = new ManagerData();
    private static ArrayList<Manager> managerList;
    public static ManagerData getInstance() {
        return ourInstance;
    }

    private ManagerData() {
        //初始化数据
        managerList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            //每循环一次，诞生一个manager对象，存入到集合中
            Manager manager = new Manager();
            manager.setAccount("1" + i);
            manager.setPassword("123");
            manager.setMname("管理员1" + i);
            managerList.add(manager);
        }
    }

    public static ArrayList<Manager> getManagerList() {
        return managerList;
    }

    public static void setManagerList(ArrayList<Manager> managerList) {
        ManagerData.managerList = managerList;
    }
}
