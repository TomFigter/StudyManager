package com.thtf.leanpackage.plugin_hook;


import com.thtf.leanpackage.plugin_hook.demo.Boy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import retrofit2.http.Body;

public class HookMian {
    public static void main(String[] args) {
        try {
            Class boyClass3 = Class.forName("com.thtf.leanpackage.plugin_hook.demo.Boy");
            realizeField(boyClass3);
            realizeMthod(boyClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void realizeField(Class boyClass3) {
        try {
            Object boy = boyClass3.newInstance();
            Field nameField = boyClass3.getDeclaredField("age");
            nameField.setAccessible(true);
            nameField.setInt(boy, 15);
            System.out.println(nameField.getInt(boy));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void realizeMthod(Class boyClass) {
        try {
            Object girlInstance = boyClass.newInstance();
            Method girlHobbyMethod = boyClass.getDeclaredMethod("boyHobby", String.class);
            girlHobbyMethod.setAccessible(true);
            Object result = girlHobbyMethod.invoke(girlInstance, "11");
            System.out.println("我执行的结果: " + result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void test() {

        //Object.getClass()
        Boy boy = new Boy();
        Class boyClass1 = boy.getClass();
        System.out.println(boyClass1.getCanonicalName());

        //Class Name.class
        Class boyClass2 = Boy.class;
        System.out.println(boyClass2.getCanonicalName());

        //Class.forName(包名+类名)
        try {
            Class boyClass3 = Class.forName("com.thtf.leanpackage.plugin_hook.demo.Boy");
            Class girlClass = Class.forName("com.thtf.leanpackage.plugin_hook.demo.Girl");
            System.out.println(girlClass.getCanonicalName());
            System.out.println(boyClass3.getCanonicalName());
            //构造器
            Constructor[] boyConstructors = boyClass3.getConstructors();
            for (Constructor c : boyConstructors) {
                System.out.println("getConstructor:" + c.toString());
            }

            boyConstructors = boyClass3.getDeclaredConstructors();
            for (Constructor c : boyConstructors) {
                System.out.println("getDeclaredConstructors:" + c.toString());
            }

            //获取指定方法属性
            Method boyHobby = boyClass3.getDeclaredMethod("boyHobby", String.class);
            Field ageField = boyClass3.getDeclaredField("age");

            System.out.println("获取的方法名：" + boyHobby.getName());
            System.out.println("获取的属性：" + ageField.getName());

            //获取反射类中的公共方法
            Method girlLittleName = girlClass.getMethod("girlLittleName", String.class);
            System.out.println("获取的公共方法名：" + girlLittleName.getName());
            //获取所有方法
            Method[] methods = girlClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Declared Method :" + method.getName());
            }
            //获取公共方法
            methods = girlClass.getMethods();
            for (Method method : methods) {
                System.out.println("Public Method :" + method.getName());
            }

            //获取所有的属性
            Field[] filed1 = boyClass3.getDeclaredFields();

            for (Field f : filed1) {
                System.out.println("Declared Field :" + f.getName());
            }
            //获取公共的字段属性
            Field[] filed2 = boyClass3.getFields();

            for (Field f : filed2) {
                System.out.println("Field :" + f.getName());
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
