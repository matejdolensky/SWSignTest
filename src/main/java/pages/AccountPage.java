package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private WebDriver driver;

    private By userInfoLanguageBy = By.id("userInfoLanguage");
    private By editUserInfoLanguage = By.id("editUserInfoLanguage");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openCorrespondenceLanguageSetting(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editUserInfoLanguage));

        driver.findElement(editUserInfoLanguage).click();

    }


    public String getCurrentCorrespondingLanguge() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userInfoLanguageBy));

        WebElement userInfoLanguage = driver.findElement(userInfoLanguageBy);

        return userInfoLanguage.getText();

    }
}
