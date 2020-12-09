package testng.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        method.getTestMethod().setRetryAnalyzerClass(RetryAnalyzer.class);
        method.getTestMethod().setInvocationCount(RetryAnalyzer.getRetryLimit());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!method.getTestMethod().hasMoreInvocation()) {
            method.getTestMethod().setRetryAnalyzerClass(null);
        }
    }
}
