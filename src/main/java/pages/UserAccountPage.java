package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserAccountPage {

    private WebDriver driver;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By languageSelectBy = By.id("languages");
    private final By saveButtonBy = By.id("confirm");

    public void selectLanguage(String languageCode){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(languageSelectBy));

        WebElement languangeSelectElement = driver.findElement(languageSelectBy);
        Select languageSelect = new Select(languangeSelectElement);
        languageSelect.selectByValue(languageCode);


        driver.findElement(saveButtonBy).click();


    }

}
