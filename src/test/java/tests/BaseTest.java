package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.TestResult;
import pages.HomePage;

import utils.extentreports.ConfigurationManager;
import config.driver.DriverFactory;
import utils.logs.Log;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    static WebDriver driver;
    static HomePage  homePage;
    static String baseURL = "http://www.n11.com/";
    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    static void classLevelSetup(ITestContext context) throws MalformedURLException {
        Log.info("Tests is starting!");
        ConfigurationManager.setITestContext(context);
        driver = DriverFactory.setupDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        context.setAttribute("WebDriver",driver);
    }

    @BeforeMethod
    static void methodLevelSetup() {

        homePage = new HomePage(driver);

    }

//    @AfterMethod
//    public void after(ITestResult iTestResult)
//    {
//
//    }

    @AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
        driver.quit();
    }
}