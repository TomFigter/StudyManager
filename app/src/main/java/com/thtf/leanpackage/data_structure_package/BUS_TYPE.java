package com.thtf.leanpackage.data_structure_package;

/**
 * Created by LiShiChuang on 2019/1/2.
 */
public class BUS_TYPE {
    String name;
    int age;

    public BUS_TYPE(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "BUS_TYPE[ NAME= " + name + " , AGE= " + age + " ]\n";
    }
}
