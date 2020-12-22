package testng.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.*;


@Log4j2
public class TestListener implements IInvokedMethodListener, ITestListener {

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

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " SKIPPED");
    }


}
