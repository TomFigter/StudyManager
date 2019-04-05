package com.thtf.leanpackage.event_bus_package.bean;

/**
 * Created by LiShiChuang on 2018/12/20.
 */
public class MESSAGE_DTO {
    String name;
    int age;
    String sayInfo;

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

    public String getSayInfo() {
        return sayInfo;
    }

    public void setSayInfo(String sayInfo) {
        this.sayInfo = sayInfo;
    }

    @Override
    public String toString() {
        return "{" +
                "名字->" + name +
                "年龄->" + age +
                "要说的话->" + sayInfo
                + "}\n";
    }
}
