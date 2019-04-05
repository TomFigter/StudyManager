package com.thtf.gradleplugin.demo_plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class Extension1 {
    String testVariable1 = null;
}

class Extension2 {
    String testVariable2 = null;
}

class myGradlePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        //利用Extension创建e1,e2闭包,用于接受外部传递的参数值
        project.extensions.create("e1", Extension1)
        project.extensions.create("e2", Extension2)

        //创建readExtension Task 执行该Task 进行参数值的读取以及自定义逻辑...
        project.task("readExtension") {
            doFirst {
                println('e1= ' + project['e1'].testVariable1)
                println('e2= ' + project['e2'].testVariable2)
            }
        }

        println("----------------Li-------------------")
        println("----------------Shi-------------------")
        println("----------------Chuang-------------------")
        println("----------------Plugin-------------------")
    }

}