package utils.extentreports;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {

    static Map<Integer, ITestContext> iTestContextMap = new HashMap<Integer, ITestContext>();

    static Map<Integer,Map<String,String>> testDataMap = new HashMap<>();
    static Map<Integer,String> testCaseNameMap = new HashMap<>();
    static Map<Integer, WebDriver> webDriverMap = new HashMap<>();

    public static synchronized Map<String,String> getTestDataMap()
    {
        return testDataMap.get((int) Thread.currentThread().getId());

    }

    public static synchronized void setTestDataMap(Map<String,String> map)
    {
        testDataMap.put((int) Thread.currentThread().getId(), map);

    }


    public static synchronized void setITestContext(ITestContext context)
    {
        iTestContextMap.put((int) Thread.currentThread().getId(), context);

    }

    public static synchronized ITestContext getITestContext()
    {
        return iTestContextMap.get((int) Thread.currentThread().getId());

    }

    public static synchronized void setTestCaseName(String testCaseName)
    {
        testCaseNameMap.put((int) Thread.currentThread().getId(),testCaseName);
    }

    public static synchronized String getTestCaseName()
    {
        return testCaseNameMap.get((int) Thread.currentThread().getId());

    }
}
