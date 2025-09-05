package core;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureScreenshotExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseGuiTest) {
            WebDriver driver = ((BaseGuiTest) testInstance).driver;
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    // Use Allure lifecycle to attach
                    Allure.getLifecycle().addAttachment(
                            "Screenshot on Failure - " + context.getDisplayName(),
                            "image/png",
                            "png",
                            new ByteArrayInputStream(screenshot)
                    );
                } catch (Exception e) {
                    System.err.println("Failed to capture screenshot: " + e.getMessage());
                }
               driver.quit();
            }
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseGuiTest) {
            WebDriver driver = ((BaseGuiTest) testInstance).driver;
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseGuiTest) {
            WebDriver driver = ((BaseGuiTest) testInstance).driver;
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
