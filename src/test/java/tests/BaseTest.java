package tests;

import driver.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    //    @BeforeMethod()
//        public void setUpDriver () {
//        driver = DriverWrapper.getDriver();
//        }


    @AfterMethod
    public void close() {
        DriverWrapper.killDriverInstance();
    }

}
