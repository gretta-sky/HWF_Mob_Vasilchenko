package utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;

public class CapabilitiesConfig {

    public static UiAutomator2Options getBasicOptions() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setCapability("platformName", "Android");
        options.setCapability("appium:platformVersion", "8.1");
        options.setCapability("appium:deviceName", "Pixel 2 API 27");
        options.setCapability("appium:automationName", "UiAutomator2");
        options.setCapability("appium:appPackage", "org.wikipedia");
        options.setCapability("appium:appActivity", ".main.MainActivity");
        options.setCapability("appium:noReset", false);
        options.setCapability("appium:autoGrantPermissions", true);
        String appPath = System.getProperty("user.dir") + "/src/test/resources/app.apk";
        options.setCapability("appium:app", appPath);
        options.setCapability("appium:newCommandTimeout", 300);
        options.setCapability("appium:connectHardwareKeyboard", true);

        return options;
    }

    public static UiAutomator2Options getOptionsForApk() {
        UiAutomator2Options options = getBasicOptions();
        String appPath = System.getProperty("user.dir") + "/src/test/resources/app.apk";
        options.setCapability("appium:app", appPath);
        options.setCapability("appium:appPackage", "org.wikipedia");
        options.setCapability("appium:appActivity", ".main.MainActivity");
        return options;
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неверный URL Appium сервера", e);
        }
    }
}