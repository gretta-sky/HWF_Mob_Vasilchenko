package tests;

import org.junit.jupiter.api.BeforeEach;
import pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTest extends BaseTest {
    private MainPage mainPage;
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        mainPage = new MainPage();
    }

    @Test
    @DisplayName("Поиск 'Java' в Wikipedia и проверка первого результата")
    public void testSearchJava() {
        mainPage.openSearch();
        mainPage.enterSearchQuery("Java");
        assertTrue(mainPage.isSearchResultsDisplayed(),
                "Результаты поиска не отображаются");

        String firstResult = mainPage.getFirstResultTitle();
        assertNotNull(firstResult, "Результат поиска пустой");
        assertFalse(firstResult.isEmpty(), "Результат поиска пустой");
        assertTrue(firstResult.toLowerCase().contains("java"),
                "Первый результат '" + firstResult + "' не содержит 'Java'");
    }
}