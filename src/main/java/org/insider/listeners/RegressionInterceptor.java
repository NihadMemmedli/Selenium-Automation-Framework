package org.insider.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class RegressionInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> result = new ArrayList<>();

        for (IMethodInstance method : methods) {
            if (method.getMethod().getMethodName().startsWith("regression")) {
                result.add(method);
            }
        }

        return result;
    }
}

