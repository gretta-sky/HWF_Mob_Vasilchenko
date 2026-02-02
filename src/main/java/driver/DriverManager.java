package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CapabilitiesConfig;
import java.time.Duration;

public class DriverManager {
    private static AndroidDriver driver;
    private static WebDriverWait wait;
    private static UiAutomator2Options currentOptions;

    public static AndroidDriver initializeDriver(UiAutomator2Options options) {
        if (driver == null) {
            currentOptions = options;
            driver = new AndroidDriver(CapabilitiesConfig.getAppiumServerUrl(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }

    public static AndroidDriver getDriver() {
        if (driver == null) {
            initializeDriver(CapabilitiesConfig.getOptionsForApk());
        }
        return driver;
    }

    public static AndroidDriver getDriver(UiAutomator2Options options) {
        if (driver != null) {
            quitDriver();
        }
        return initializeDriver(options);
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        }
        return wait;
    }

    public static UiAutomator2Options getCurrentOptions() {
        return currentOptions;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
            currentOptions = null;
        }
    }
    public static AndroidDriver restartDriver(UiAutomator2Options options) {
        quitDriver();
        return initializeDriver(options);
    }
}