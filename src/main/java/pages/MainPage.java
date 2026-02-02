package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage extends BasePage {
    private final String searchContainerId = "org.wikipedia:id/search_container";
    private final String searchInputId = "org.wikipedia:id/search_src_text";
    private final String searchResultsId = "org.wikipedia:id/search_results_list";
    private final String searchResultTitleId = "org.wikipedia:id/page_list_item_title";
    private final String backButtonId = "org.wikipedia:id/search_close_btn";

    public void openSearch() {
        WebElement searchContainer = driver.findElement(AppiumBy.id(searchContainerId));
        clickElement(searchContainer);
    }

    public void enterSearchQuery(String query) {
        WebElement searchInput = driver.findElement(AppiumBy.id(searchInputId));
        searchInput.clear();
        sendKeysToElement(searchInput, query);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void searchFor(String query) {
        openSearch();
        enterSearchQuery(query);
    }

    public String getFirstResultTitle() {
        List<WebElement> results = driver.findElements(AppiumBy.id(searchResultTitleId));
        if (!results.isEmpty()) {
            return getElementText(results.get(0));
        }
        return "";
    }

    public String getFirstResultText() {
        return getFirstResultTitle();
    }

    public void clearSearch() {
        WebElement backButton = driver.findElement(AppiumBy.id(backButtonId));
        clickElement(backButton);
    }

    public boolean isSearchResultsDisplayed() {
        try {
            WebElement resultsContainer = driver.findElement(AppiumBy.id(searchResultsId));
            return resultsContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstResultDescription() {
        List<WebElement> descriptions = driver.findElements(
                AppiumBy.id("org.wikipedia:id/page_list_item_description")
        );
        if (!descriptions.isEmpty()) {
            return getElementText(descriptions.get(0));
        }
        return "";
    }
    public boolean isOnboardingDisplayed() {
        try {
            WebElement continueButton = driver.findElement(
                    AppiumBy.id("org.wikipedia:id/fragment_onboarding_forward_button")
            );
            return continueButton.isDisplayed();
        } catch (Exception e) {
            try {
                WebElement acceptButton = driver.findElement(
                        AppiumBy.id("org.wikipedia:id/acceptButton")
                );
                return acceptButton.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }
}