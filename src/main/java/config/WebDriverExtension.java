package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver, TestWatcher {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(WebDriverExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        context.getStore(NAMESPACE).put("driver", driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriver driver = getDriver(context);
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = getDriver(context);
        if (driver != null) {
            attachScreenshot(driver);
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == WebDriver.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) {
        return getDriver(extensionContext);
    }

    private WebDriver getDriver(ExtensionContext context) {
        return context.getStore(NAMESPACE).get("driver", WebDriver.class);
    }
}
