package config.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class CapabilitiesProvider {

    public static FirefoxOptions getFirefoxCapabilities(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        firefoxOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL);
        firefoxOptions.addPreference("security.insecure_field_warning.contextual.enabled",false);
        firefoxOptions.setAcceptInsecureCerts(true);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

        return firefoxOptions;

    }

    public static ChromeOptions getChromeCapabilities(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        chromeOptions.addArguments("--log-level=3");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setAcceptInsecureCerts(true);
        return chromeOptions;

    }

    public static EdgeOptions getEdgeCapabilities(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        edgeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        edgeOptions.addArguments("--log-level=3");
        edgeOptions.addArguments("--ignore-certificate-errors");
        edgeOptions.setAcceptInsecureCerts(true);
        return edgeOptions;

    }

}
