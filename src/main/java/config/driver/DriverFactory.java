package config.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import utils.extentreports.ConfigurationManager;

import java.net.MalformedURLException;

public class DriverFactory {

    public static String browserName;

    public static WebDriver startDriver(String desiredDriver) throws MalformedURLException
    {
        WebDriver driver = null;

        if(desiredDriver.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver(CapabilitiesProvider.getChromeCapabilities());
        }
        else if(desiredDriver.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver(CapabilitiesProvider.getEdgeCapabilities());
        }
        else if(desiredDriver.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver(CapabilitiesProvider.getFirefoxCapabilities());
        }

        return driver;
    }

    public static WebDriver setupDriver() throws MalformedURLException
    {
        ITestContext context = ConfigurationManager.getITestContext();
        browserName = context.getCurrentXmlTest().getParameter("browser");
        return startDriver(System.getProperty("browser",browserName));
    }
}
