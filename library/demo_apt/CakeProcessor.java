package com.thtf.apt.demo_apt;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by LiShiChuang on 2018/12/5.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CakeProcessor extends AbstractProcessor {
    /**
     * 用来处理注解
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        int size = roundEnv.getElementsAnnotatedWith(GetMsg.class).size();
        messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class size= " + size);
        
        for (Element element : roundEnv.getElementsAnnotatedWith(GetMsg.class)) {
            PackageElement packageElement = (PackageElement) element.getEnclosingElement();
            //获取该注解所在类的包名
            String packageName = packageElement.getQualifiedName().toString();
            TypeElement classElement = (TypeElement) element;
            //获取该注解所在类的类名
            String className = classElement.getSimpleName().toString();
            //获取该注解所在类的全类名
            String fullClassName = classElement.getQualifiedName().toString();
//            VariableElement variableElement = (VariableElement) element.getEnclosingElement();

            //获取方法名
//            String methodName = variableElement.getSimpleName().toString();
            //获取该注解的值
            int id = classElement.getAnnotation(GetMsg.class).id();
            String name = classElement.getAnnotation(GetMsg.class).name();

            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class: packageName= " + packageName);
            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class: className= " + className);
            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class: fullClassName= " + fullClassName);
//            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class: methodName= " + methodName);
            messager.printMessage(Diagnostic.Kind.NOTE, "Annotation class: id= " + id + " name= " + name);
            System.out.println("Annotation class: packageName= " + packageName);
            System.out.println("Annotation class: className= " + className);
            System.out.println("Annotation class: fullClassName= " + fullClassName);
            System.out.println("Annotation class: id= " + id + " name= " + name);
        }
        return true;
    }

    /**
     * 用来表示该Processor处理哪些注解;
     * <p>
     * 这里我们只有一个GetMsg注解需要处理.
     *
     * @return
     */

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> typeSet = new LinkedHashSet<>();
        typeSet.add(GetMsg.class.getCanonicalName());
        return typeSet;
    }
}
