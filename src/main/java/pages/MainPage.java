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

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButtonBy = By.cssSelector(".klp-widget-anonymous__text");
    private final By profileButtonBy = By.cssSelector("#post-klp-login-widget > div.klp-widget-authenticated > div > a");
    private final By authenticatedMenuBy = By.id("authenticated-menu");
    private final By rootElementBy = By.id("usercentrics-root");
    private final By loginHeaderRootElementBy = By.cssSelector("swisspost-internet-header");
    private final By acceptCookiesButton = By.cssSelector("[data-testid='uc-accept-all-button']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    @Step("Click on login button")
    public void clickOnLoginButton() {

        SearchContext shadowRootElement = driver.findElement(rootElementBy).getShadowRoot();

        WebElement acceptCookiesElement = wait.until(driver -> {
            WebElement el = shadowRootElement.findElement(acceptCookiesButton);
            return (el.isDisplayed()) ? el : null;
        });


        acceptCookiesElement.click();


        SearchContext firstLoginShadowRoot = driver.findElement(loginHeaderRootElementBy).getShadowRoot();
        SearchContext secondLoginShadowRoot = firstLoginShadowRoot.findElement(By.cssSelector(".main-navigation-controls")).findElement(By.cssSelector("post-klp-login-widget")).getShadowRoot();

        WebElement loginButtonElement = wait.until(driver -> {
            WebElement el = secondLoginShadowRoot.findElement(loginButtonBy);
            return (el.isDisplayed()) ? el : null;
        });

        loginButtonElement.click();
    }

    @Step("Go to my profile from main page")
    public void goToMyProfile() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginHeaderRootElementBy));

        SearchContext firstLoginShadowRoot = driver.findElement(loginHeaderRootElementBy).getShadowRoot();
        SearchContext secondLoginShadowRoot = firstLoginShadowRoot.findElement(By.cssSelector(".main-navigation-controls")).findElement(By.cssSelector("post-klp-login-widget")).getShadowRoot();

        wait.until(driver -> {
            WebElement el = secondLoginShadowRoot.findElement(profileButtonBy);
            return (el.isDisplayed()) ? el : null;
        });

        secondLoginShadowRoot.findElement(profileButtonBy).click();

        wait.until(driver -> {
            WebElement el = secondLoginShadowRoot.findElement(authenticatedMenuBy);
            return (el.isEnabled()) ? el : null;
        });

        secondLoginShadowRoot.findElement(authenticatedMenuBy).findElements(By.cssSelector(".notification-link")).get(0).click();
    }
}
