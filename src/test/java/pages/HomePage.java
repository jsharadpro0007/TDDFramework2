package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.logs.Log;

public class HomePage extends BasePage {
    String baseURL = "http://www.n11.com/";
    By signInButtonClass = By.className("btnSignIn");

    public HomePage(WebDriver driver) {
        super(driver);
    }

//    public HomePage goToN11() {
//        Log.info("Opening N11 Website.");
//        driver.get(baseURL);
//        return this;
//    }

    //Go to LoginPage
    public LoginPage goToLoginPage() {
        Log.info("Going to Login Page..");
        click(signInButtonClass);
        return new LoginPage(driver);
    }
}