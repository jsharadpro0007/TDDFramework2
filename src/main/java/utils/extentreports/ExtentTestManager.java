package utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.internal.TestResult;
import utils.datareader.ExcelTestData;

import javax.management.monitor.GaugeMonitor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling getExtentReports() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports            extent        = ExtentManager.getExtentReports();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized Serializable startTest(String testName, String testId) throws Exception {
        ExtentTest test = extent.createTest(testName, testId);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        System.out.println("*****************Test started****************");
        ConfigurationManager.setTestCaseName(testId);
        ExcelTestData data = new ExcelTestData();
        Map<String, String> testDataRow = null;
        testDataRow=data.getTestDataRow(testId);
        ConfigurationManager.setTestDataMap(testDataRow);
        System.out.println("Test Data has been setup successfully");

        return test;
    }

    public static boolean addScreenShotAsBase64(WebDriver driver)
    {
        ExtentTest test = extentTestMap.get((int) Thread.currentThread().getId());
        String base64ScreenShot = "data:image/png;base64,"+((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.addScreenCaptureFromBase64String(base64ScreenShot).log(Status.INFO,"screenshot");
        return true;
    }
}