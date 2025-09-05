package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    private final By loginButtonBy = By.cssSelector(".klp-widget-anonymous__text");
    private final By profileButtonBy = By.cssSelector("#post-klp-login-widget > div.klp-widget-authenticated > div > a");
    private final By authenticatedMenuBy = By.id("authenticated-menu");
    private final By rootElementBy = By.id("usercentrics-root");
    private final By loginHeaderRootElementBy = By.cssSelector("swisspost-internet-header");
    private final By acceptCookiesButton = By.cssSelector("[data-testid='uc-accept-all-button']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Click on login button")
    public void clickOnLoginButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SearchContext shadowRootElement = driver.findElement(rootElementBy).getShadowRoot();

        WebElement acceptCookiesElement = wait.until(driver1 -> {
            WebElement el = shadowRootElement.findElement(acceptCookiesButton);
            return (el.isDisplayed()) ? el : null;
        });


        acceptCookiesElement.click();


        SearchContext firstLoginShadowRoot = driver.findElement(loginHeaderRootElementBy).getShadowRoot();
        SearchContext secondLoginShadowRoot = firstLoginShadowRoot.findElement(By.cssSelector(".main-navigation-controls")).findElement(By.cssSelector("post-klp-login-widget")).getShadowRoot();

        WebElement loginButtonElement = wait.until(driver1 -> {
            WebElement el = secondLoginShadowRoot.findElement(loginButtonBy);
            return (el.isDisplayed()) ? el : null;
        });

        loginButtonElement.click();
    }

    public void goToMyProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginHeaderRootElementBy));

        SearchContext firstLoginShadowRoot = driver.findElement(loginHeaderRootElementBy).getShadowRoot();
        SearchContext secondLoginShadowRoot = firstLoginShadowRoot.findElement(By.cssSelector(".main-navigation-controls")).findElement(By.cssSelector("post-klp-login-widget")).getShadowRoot();

        wait.until(driver1 -> {
            WebElement el = secondLoginShadowRoot.findElement(profileButtonBy);
            return (el.isDisplayed()) ? el : null;
        });

        secondLoginShadowRoot.findElement(profileButtonBy).click();

        wait.until(driver1 -> {
            WebElement el = secondLoginShadowRoot.findElement(authenticatedMenuBy);
            return (el.isEnabled()) ? el : null;
        });

        secondLoginShadowRoot.findElement(authenticatedMenuBy).findElements(By.cssSelector(".notification-link")).get(0).click();


    }
}
