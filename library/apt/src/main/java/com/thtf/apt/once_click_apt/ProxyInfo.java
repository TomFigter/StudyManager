package com.thtf.apt.once_click_apt;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;

/**
 * Created by LiShiChuang on 2018/12/13.
 */
public class ProxyInfo {
    private String packageName;
    private String targetClassName;
    private String proxyClassName;
    private TypeElement typeElement;
    private List<OnceMethod> methods;
    public static final String PROXY = "PROXY";

    ProxyInfo(String packageName, String className) {
        this.packageName = packageName;
        this.targetClassName = className;
        this.proxyClassName = className + "$$" + PROXY;
    }

    String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    String generateJavaCode() throws OnceClickException {
        StringBuilder builder = new StringBuilder();
        builder.append("//Generated code from OnceClick,Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import android.view.View;\n");
        builder.append("import com.thtf.onceclickapt.Finder;\n");
        builder.append("import com.thtf.onceclickapt.AbstractInjector;\n");
        builder.append("\n");

        builder.append("public class ").append(proxyClassName);
        builder.append("<T extends ").append(getTargetClassName()).append(">");
        builder.append("implements AbstractInjector<T>");
        builder.append("{");
        generateInjectMethod(builder);
        builder.append("\n").append("}");
        return builder.toString();
    }

    private void generateInjectMethod(StringBuilder builder) throws OnceClickException {
        builder.append("public long intervalTime;\n");
        builder.append("@Override\n")
                .append("public void setIntervalTime(long time){\n")
                .append("intervalTime=time;\n}\n");
        builder.append("@Override\n")
                .append("public void inject(final Finder filter,final T target,Object source){\n");
        builder.append("View view;\n");
        for (OnceMethod method : getMethods()) {
            builder.append("  view= ")
                    .append("filter.findViewById(source,")
                    .append(method.getId())
                    .append(");\n");
            builder.append("if(view!=null){")
                    .append("view.setOnClickListener(new View.OnClickListener(){\n")
                    .append("long time=0L;");
            builder.append("@Override\n")
                    .append("public void onClick(View v){");
            builder.append("long temp=System.currentTimeMillis();\n")
                    .append("if(temp-time>=intervalTime){\n")
                    .append("time=temp;\n");
            if (method.getMethodParametersSize() == 1) {
                if (method.getMethodParameters().get(0).equals("android.view.View")) {
                    builder.append("target.").append(method.getMethodName()).append("(v);");
                } else {
                    throw new OnceClickException("Parameters must be android.view.View");
                }
            } else if (method.getMethodParametersSize() == 0) {
                builder.append("target.").append(method.getMethodName()).append("();");
            } else {
                throw new OnceClickException("Does not support more than one parameter");
            }
            builder.append("\n}")
                    .append("   \n}")
                    .append("       });\n");
        }

        builder.append("  }\n");
        builder.append("  }\n");
    }

    TypeElement getTypeElement() {
        return typeElement;
    }

    void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    List<OnceMethod> getMethods() {
        return methods == null ? new ArrayList<OnceMethod>() : methods;
    }

    void addMethod(OnceMethod onceMethod) {
        if (methods == null) {
            methods = new ArrayList<>();
        }
        methods.add(onceMethod);
    }

    private String getTargetClassName() {
        return targetClassName.replace("$", ".");
    }

}
