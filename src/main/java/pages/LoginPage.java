package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailAddressBy = By.id("email");
    private By passwordBy = By.id("password");
    private By nextButtonBy = By.cssSelector("#login-email");
    private By logInWithPasswordBy = By.id("LOGIN_WITH_PASSWORD");


    public void fillOutEmailAddress(String emailAddress) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(emailAddressBy));
        WebElement emailAddressField = driver.findElement(emailAddressBy);
        emailAddressField.sendKeys(emailAddress);
        emailAddressField.sendKeys(Keys.RETURN);
    }


    public void clickOnLoginWithPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(logInWithPasswordBy));
        driver.findElement(logInWithPasswordBy).findElement(By.cssSelector("button")).click();
    }

    public void fillOutPassword(String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(passwordBy));
        WebElement passwordField = driver.findElement(passwordBy);
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.RETURN);

        System.out.println("");

    }

}
