package testng.listeners;

import environment.Environment;
import lombok.Getter;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {

    @Getter
    private static int retryLimit;

    static {
        if (Environment.getEnvProperty("retryOnFail") != null) {
            retryLimit = Integer.parseInt(Environment.getEnvProperty("retryOnFail"));
        } else {
            retryLimit = 0;
        }
    }

    private int counter = 0;


    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (counter < retryLimit) {
                counter++;
                return true;
            }
        }
        return false;
    }
}

