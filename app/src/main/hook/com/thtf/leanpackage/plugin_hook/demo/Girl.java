package com.thtf.leanpackage.plugin_hook.demo;

class Girl {
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String girlHobby(String mineHobby) {
        String brief = "My name is" + name + ", I'm " + age + "year old.\n";
        String hobby = "我的爱好是" + mineHobby;
        return brief + hobby;
    }
    public String  girlLittleName(String littleName){
        return littleName;
    }
}
