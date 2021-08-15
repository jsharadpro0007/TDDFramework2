package base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;

import static utils.extentreports.ExtentTestManager.getTest;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    private final String waitTimeout = "60";

    //Constructor
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        jsExecutor = ((JavascriptExecutor) driver);
    }

    public BasePage() {

    }

    protected WebElement clickOn(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            waitForClickable(element).click();
        }
        return element;
    }

    protected WebElement clickOn(By by) {
        WebElement element;
        try {
            element = driver.findElement(by);
            element.click();
        } catch (Exception e) {
            element = waitForClickable(by);
            element.click();
        }
        return element;
    }


    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void switchToSpecificWindow(int index) {
        ArrayList<String> winHandles = new ArrayList(driver.getWindowHandles());
        for (int i = 0; i < winHandles.size(); i++) {
            driver.switchTo().window(winHandles.get(i));
        }
    }

    protected void switchToWindowByTitle(String title) {
        try {
            for (String winHandle : driver.getWindowHandles()) {
                if (driver.switchTo().window(winHandle).getTitle().equalsIgnoreCase(title)) {
                    driver.switchTo().window(winHandle);
                }
            }
        } catch (NoSuchWindowException e) {
            e.printStackTrace();
        }
    }

    //Switch to the new Window -- works well in case of only two windows
    protected void switchToNewWindow(String currentWindow) {
        for (String winHandle : driver.getWindowHandles()) {
            if (!currentWindow.equals(winHandle)) {
                driver.switchTo().window(winHandle);
            }
        }
    }

    //Click Method
    protected void click(By by) {
        waitVisibility(by).click();
    }

    //Write Text
    protected void writeText(By by, String text) {
        waitVisibility(by).sendKeys(text);
    }

    //Read Text
    protected String readText(By by) {
        return waitVisibility(by).getText();
    }

    //Wait
    protected WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String addScreenShot(WebDriver driver) {
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

        return base64Screenshot;
        //ExtentReports log and screenshot operations for failed tests.
        //  getTest().log(Status.INFO,"Screenshot",getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    public void attachScreenShot(WebDriver driver) {
        String base64Screenshot = ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());


    }

}

