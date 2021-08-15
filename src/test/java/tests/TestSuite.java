package tests;

import static utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;
import utils.datareader.ExcelTestData;
import utils.listeners.Retry;

public class TestSuite extends BaseTest {
    private ExcelTestData data = new ExcelTestData();
    @Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.",retryAnalyzer = Retry.class)
    public void invalidLoginTest_InvalidUserNameInvalidPassword(ITestContext context) throws Exception {
        //ExtentReports Description
        startTest("Invalid Login Scenario with wrong username and password.", "Test001");

        if(data.getTestData("Runmode").equals("N"))
        {
            throw new SkipException("Skipping the test case");
        }
        WebDriver driver  = (WebDriver) context.getAttribute("WebDriver");
//        driver.get("https://www.google.com/");
//        driver.manage().window().maximize();

        homePage
            .goToLoginPage()
            .loginToN11(data.getTestData("User Name"), data.getTestData("Password"))
            .verifyLogError();

    }

//    @Test(priority = 1, description = "Invalid Login Scenario with empty username and password.")
//    public void invalidLoginTest_EmptyUserEmptyPassword(Method method) {
//        //ExtentReports Description
//        startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
//
//        homePage
//            .goToN11()
//            .goToLoginPage()
//            .loginToN11("", "")
//            .verifyLoginUserName("Lütfen e-posta adresinizi girin.")
//            .verifyLoginPassword("WRONG MESSAGE FOR FAILURE!");
//    }
}