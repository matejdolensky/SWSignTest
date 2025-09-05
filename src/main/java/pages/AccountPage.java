package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private final WebDriver driver;

    private final By userInfoLanguageBy = By.id("userInfoLanguage");
    private final By editUserInfoLanguage = By.id("editUserInfoLanguage");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open correspondence language setting")
    public void openCorrespondenceLanguageSetting(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editUserInfoLanguage));

        driver.findElement(editUserInfoLanguage).click();

    }

    @Step("Get current set corresponding language")
    public String getCurrentCorrespondingLanguage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userInfoLanguageBy));

        WebElement userInfoLanguage = driver.findElement(userInfoLanguageBy);

        return userInfoLanguage.getText();

    }
}
