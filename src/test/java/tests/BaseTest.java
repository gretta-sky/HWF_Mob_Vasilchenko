package tests;

import driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class BaseTest {
    protected WebDriverWait wait;
    @BeforeEach
    public void setUp() {
        UiAutomator2Options options = configureCapabilities();
        initializeAppiumSession(options);
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        handleOnboardingScreens();
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected UiAutomator2Options configureCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setCapability("platformName", "Android");
        options.setCapability("appium:platformVersion", "8.1");
        options.setCapability("appium:deviceName", "Pixel 2 API 27");
        options.setCapability("appium:automationName", "UiAutomator2");
        options.setCapability("appium:appPackage", "org.wikipedia");
        options.setCapability("appium:appActivity", ".main.MainActivity");
        String appPath = System.getProperty("user.dir") + "/src/test/resources/app.apk";
        options.setCapability("appium:app", appPath);
        options.setCapability("appium:noReset", false);
        options.setCapability("appium:fullReset", false);
        options.setCapability("appium:autoGrantPermissions", true);
        options.setCapability("appium:newCommandTimeout", 300);
        options.setCapability("appium:connectHardwareKeyboard", true);

        return options;
    }

    private void initializeAppiumSession(UiAutomator2Options options) {
        DriverManager.initializeDriver(options);
    }

    protected void handleOnboardingScreens() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Не удалось совершить методы пропуска", e);
        }
        handleContinueButtons();
        handleAcceptButton();
    }

    private void handleContinueButtons() {
        String continueButtonId = "org.wikipedia:id/fragment_onboarding_forward_button";
        int maxAttempts = 5;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                WebElement continueButton = wait.withTimeout(Duration.ofSeconds(3))
                        .until(ExpectedConditions.presenceOfElementLocated(
                                AppiumBy.id(continueButtonId)
                        ));

                if (continueButton.isDisplayed()) {
                    continueButton.click();
                    attempts++;
                    Thread.sleep(2000);
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
    }

    private void handleAcceptButton() {
        String acceptButtonId = "org.wikipedia:id/acceptButton";

        try {
            WebElement acceptButton = wait.withTimeout(Duration.ofSeconds(3))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.id(acceptButtonId)
                    ));

            if (acceptButton.isDisplayed()) {
                acceptButton.click();
                Thread.sleep(2000);
                wait.until(ExpectedConditions.invisibilityOf(acceptButton));
            }
        } catch (Exception ignored) {
        }
    }
    protected boolean isElementDisplayed(String elementId) {
        try {
            return DriverManager.getDriver()
                    .findElement(AppiumBy.id(elementId))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private void waitForMainScreen() {
        String mainScreenElementId = "org.wikipedia:id/main_toolbar";

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id(mainScreenElementId)
            ));
        } catch (Exception e) {
            throw new RuntimeException("Не удалось открыть главную страницу", e);
        }
    }

    protected void clickIfDisplayed(String elementId) {
        if (isElementDisplayed(elementId)) {
            try {
                DriverManager.getDriver()
                        .findElement(AppiumBy.id(elementId))
                        .click();
            } catch (Exception e) {
                throw new RuntimeException("Не удалось кликнуть на элемент ", e);
               }
        }
    }
}