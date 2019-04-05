package com.thtf.apt.once_click_apt;

import java.util.List;

/**
 * Created by LiShiChuang on 2018/12/13.
 */
public class OnceMethod {
    private int id;
    private String methodName;
    private List<String> methodParameters;

    OnceMethod(int id, String methodName, List<String> methodParameters) {
        this.id = id;
        this.methodName = methodName;
        this.methodParameters = methodParameters;
    }

    int getMethodParametersSize() {
        return methodParameters == null ? 0 : methodParameters.size();
    }

    int getId() {
        return id;
    }

    String getMethodName() {
        return methodName;
    }

    List<String> getMethodParameters() {
        return methodParameters;
    }
}
