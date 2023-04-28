package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
import java.util.List;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
        FOLDER_BY_NAME_TPL,
        MY_SAVED_ARTICLE,
        REMOVE_FROM_SAVED_BUTTON,
        REMOVE_ARTICLE_FROM_SAVED,
        ARTICLES_LIST,
        ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Get the reading list by name '{name_of_folder}'")
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    @Step("Get the article xpath by title '{article_title}'")
    private static String getArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    @Step("Get the remove button of the article with the title '{article_title}'")
    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    @Step("Opening the reading list by name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find created folder" + name_of_folder,
                10
        );
    }

//    @Step("Get the article titles list")
//    public List getArticleTitleList(){
//        List<WebElement> webElements = this.waitForPresenceOfAllElements(ARTICLE_BY_TITLE, "Cannot find any article", 15);
//        List<String> titles = new ArrayList<>();
//        for (WebElement element: webElements) {
//            titles.add(element.getText());
//        }
//        return titles;
//    }

    @Step("Waiting for the article with the title '{article_title}' to appear")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title" + article_title,
                10
        );
    }

    @Step("Waiting for the article with the title '{article_title}' to disappear")
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title" + article_title,
                15
        );
    }

    @Step("Deleting the article '{article_title}'")
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getArticleXpathByTitle(article_title);

        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())){
        this.swipeElementToLeft(
                article_xpath,
                "Did not happen");
        }else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath,"Cannot find Saved article");
        }
        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Holding tap to delete article {article_title}")
    public void holdArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getArticleXpathByTitle(article_title);
        this.tapElementAndHold(article_xpath,
                "Cannot see the article",
                6000);
    }

    @Step("Deleting saved article")
    public void deleteSavedArticle()
    {
        this.waitForElementPresent(
                REMOVE_ARTICLE_FROM_SAVED,
                "Cannot find remove button",
                5
        );
        this.waitForElementAndClick(
                REMOVE_ARTICLE_FROM_SAVED,
                "Cannot remove article",
                15
        );
    }

//    @Step("Get amount of added articles in the reading list")
//    public int getAmountOfAddedArticles() {
//        this.waitForElementPresent(ARTICLE_LIST_ITEM, "There is nothing by request", 15);
//        return getAmountOfElements(ARTICLE_LIST_ITEM);
//    }

    public void clickByArticleWithTitle(String articleTitle) {
        String articleTitleXpath = getArticleXpathByTitle(articleTitle);
        this.waitForElementClickableAndClick(articleTitleXpath, String.format("Article with the title '%s' not found or disabled", articleTitle), 5);
    }

    @Step("Adding article to the list")
    public List<String> addArticlesToList()
    {
        List<String> articles = new LinkedList<String>();

        By by = getLocatorByString(ARTICLES_LIST);
        List<WebElement> elements = driver.findElements(by);
        for(WebElement element : elements) {
            articles.add(element.getAttribute("name"));
        }
        return articles;
    }
}