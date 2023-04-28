package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    @Feature(value = "Search")
    @DisplayName("Simple search for the article")
    @Description("Search for the article and check its presence in search results by substring")
    @Step("Starting test 'testSearch'")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }
    @Test
    @Feature(value = "Search")
    @DisplayName("Cancel search for the article")
    @Description("Search for the article and then cancel the search")
    @Step("Starting test 'testCancelSearch'")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }


//    @Test
//    public void testSwipeTillText()
//    {
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find search Wikipedia input",
//                5
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search…')]"),
//                "Appium",
//                "Cannot find search input",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Automation for Apps')]"),
//                "Cannot find search Wikipedia input",
//                5
//        );
//        MainPageObject.swipeUpToFindElement(
//                By.xpath("//*[@text='View page in browser']"),
//                "Could not find the end of the article",
//                20
//        );

    @Test
    @Feature(value = "Search")
    @DisplayName("Search for not empty results")
    @Description("Search for the article and then check that search results are not empty")
    @Step("Starting test 'testAmountOfNotEmptySearch'")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }
    @Test
    @Feature(value = "Search")
    @DisplayName("Search for empty results")
    @Description("Search for the article and then check that search results are empty")
    @Step("Starting test 'testAmountOfEmptySearch'")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "asdkjhqweq";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultsOfSearch();
    }
    @Test
    @Feature(value = "Search")
    @DisplayName("Search for request and check if results more than one")
    @Description("Search for the article and then check that search results are plural")
    @Step("Starting test 'testAmountOfEmptySearch'")
    @Severity(value = SeverityLevel.MINOR)
    public void testAssertNumberOfArticles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Jimmy Carr";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 1
        );
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.initSearchInput();
    }

}
