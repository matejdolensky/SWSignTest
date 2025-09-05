package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailAddressBy = By.id("email");
    private final By passwordBy = By.id("password");
    private final By logInWithPasswordBy = By.id("LOGIN_WITH_PASSWORD");


    @Step("Fill out the email address: {emailAddress} and submit")
    public void fillOutEmailAddress(String emailAddress) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(emailAddressBy));
        WebElement emailAddressField = driver.findElement(emailAddressBy);
        emailAddressField.sendKeys(emailAddress);
        emailAddressField.sendKeys(Keys.RETURN);
    }


    @Step("Click on log in with password")
    public void clickOnLoginWithPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(logInWithPasswordBy));
        driver.findElement(logInWithPasswordBy).findElement(By.cssSelector("button")).click();
    }

    @Step("Fill out the password and submit")
    public void fillOutPassword(String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(passwordBy));
        WebElement passwordField = driver.findElement(passwordBy);
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.RETURN);

        System.out.println("");

    }

}
