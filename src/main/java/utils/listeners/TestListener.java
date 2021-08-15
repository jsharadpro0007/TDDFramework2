package utils.listeners;

import static utils.extentreports.ExtentManager.getExtentReports;
import static utils.extentreports.ExtentTestManager.getTest;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BasePage;
import org.testng.internal.TestResult;
import utils.extentreports.ConfigurationManager;
import utils.logs.Log;

public class TestListener extends BasePage implements ITestListener {

  //  BasePage base = new BasePage();
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");

        attachScreenShot(driver);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
       // WebDriver driver = ((BaseTest) testClass).getDriver();
        getTest().log(Status.FAIL, "Test Failed");
//        base.takeScreenShot(driver);


        System.out.println((iTestResult.getMethod().getMethodName() + " failed!"));
        getTest().fail(iTestResult.getThrowable());
        getTest().fail(iTestResult.getTestClass().getName() + "." + iTestResult.getMethod().getMethodName(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(addScreenShot(driver)).build());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
//        if(iTestResult.getStatus()==3) {
            Log.info(getTestMethodName(iTestResult) + " test is skipped.");
            //ExtentReports log operation for skipped tests.
            getTest().log(Status.SKIP, "Test Skipped");
//        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
