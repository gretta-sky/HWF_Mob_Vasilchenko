package pages;

import driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getWait();
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected void sendKeysToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected String getElementText(WebElement element) {
        return element.getText();
    }
    protected WebElement findElementByXpath(String xpath) {
        return driver.findElement(AppiumBy.xpath(xpath));
    }

    protected WebElement findElementById(String id) {
        return driver.findElement(AppiumBy.id(id));
    }
}