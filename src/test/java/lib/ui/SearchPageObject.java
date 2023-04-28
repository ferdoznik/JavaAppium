package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_ELEMENT,
    SEARCH_MY_SAVED_ARTICLE,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    SEARCH_RESULT_BY_LIST_ITEM_TITLE_TPL,
    SEARCH_RESULT_BY_LIST_ITEM_TITLE_AND_DESCRIPTION_TPL,
    SEARCH_RESULT_LIST_ITEM,
    SEARCH_EMPTY_RESULT_ELEMENT;
    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /*TEMPLATES METHODS */

    @Step("Get the result search by substring '{substring}'")
    public static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    @Step("Get the result search by title '{articleTitle}'")
    private static String getResultSearchElementWithTitle(String articleTitle) {
        return SEARCH_RESULT_BY_LIST_ITEM_TITLE_TPL.replace("{TITLE}", articleTitle.replace("\n", ""));
    }

    @Step("Get the found article by '{articleTitle}' and '{articleDescription}'")
    private static String getArticleWithTitleAndDescription(String articleTitle, String articleDescription) {
        return SEARCH_RESULT_BY_LIST_ITEM_TITLE_AND_DESCRIPTION_TPL
                .replace("{ARTICLE_TITLE}", articleTitle)
                .replace("{ARTICLE_DESCRIPTION}", articleDescription);
    }

    /*TEMPLATES METHODS */

    @Step("Waiting for the article with title '{title}' and '{description}' to appear")
    public void waitForElementByTitleAndDescription(String title, String description) {
        String articleWithTitleAndDescriptionXpath = "";
        if (description.contains("|")) {
            String[] values = description.split(Pattern.quote("|"), 2);
            String value1 = values[0];
            String value2 = values[1];
            articleWithTitleAndDescriptionXpath = !isElementPresent(getArticleWithTitleAndDescription(title, value1))
                    ? getArticleWithTitleAndDescription(title, value2)
                    : getArticleWithTitleAndDescription(title, value1);
        } else {
            articleWithTitleAndDescriptionXpath = getArticleWithTitleAndDescription(title, description);
        }
        this.waitForElementPresent(
                articleWithTitleAndDescriptionXpath,
                String.format("Cannot find article with the title '%s' and description '%s'.", title, description),
                15
        );
    }

    @Step("Initializing the search field")
    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 2);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find cancel button", 5);
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button still present", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Typing '{substring}' to the search line")
    public void typeSearchLine (String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line,"Cannot find and type into search_input", 2);
    }

    @Step("Waiting for search result with substring '{substring}'")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring" + substring);
    }

    @Step("Waiting for amount of found results more than '{resultsCount}'")
    public void waitForNumberOfResultsMoreThan(int resultsCount) {
        this.waitForNumberOfElementsToBeMoreThan(SEARCH_RESULT_LIST_ITEM, resultsCount, String.format("Количество найденных результатов меньше ожидаемого: %d.", resultsCount), 15);
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void waitForNotEmptySearchResults() {
        this.waitForNumberOfResultsMoreThan(0);
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring" + substring, 5);
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                5
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 10);
    }
    public void assertThereIsNoResultsOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We were supposed not to find any results");
    }
    public void waitForElementByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find and click search result with substring" + substring, 10);
    }
    @Step("Making sure there are no results fo the search")
    public void assertThereIsResultsOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We were supposed to find some results");
    }

    @Step("Checking if article is deleted")
    public void checkIfArticleIsDeleted()
    {
        this.assertElementNotPresent(
                SEARCH_MY_SAVED_ARTICLE,
                "Article is still there"
        );
    }

    @Step("Adding article to Watch list")
    public void clickAddToWatchList(){
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot add article to my lists",
                3
        );
    }
}
