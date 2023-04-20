package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
    TITLE,
    OPTIONS_REMOVE_FROM_MY_LISTS_BUTTON,
    FOOTER_ELEMENT,
    OPTIONS_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_MAIN_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON,
    LEAVE_ARTICLE_BUTTON,
    CANCEL_BUTTON,
    CLOSE_BUTTON,
    MY_SAVED_LIST,
    CLEAR_TEXT_BUTTON,
    POPOVER;
    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 3);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
        return title_element.getAttribute("text");
        }else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        }else{
            return title_element.getText();
        }
    }
    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);}
        else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
                );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find options add article to reading list",
                5
                );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'GOT IT' button",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_MAIN_INPUT,
                "Cannot find search Wikipedia input",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_MAIN_INPUT,
                name_of_folder,
                "Cannot find search input",
                5
                );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
                );
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot press X button",
                5
        );

    }
    public void closeArticle()
    {
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article",
                5
        );
        }else {
            System.out.println("Method closeArticle do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    public void addAnotherArticleToMyList()
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find options add article to reading list",
                5
        );
    }
    public void waitForPopoverToDisappear()
    {
        this.waitForElementNotPresent(POPOVER, "POPOVER still present", 5);
    }
    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Save for later button",
                2);
        this.waitForElementAndClick(
                LEAVE_ARTICLE_BUTTON,
                "Cannot find Back button",
                2);
        this.waitForElementAndClick(
                CANCEL_BUTTON,
                "Cannot find Cancel button",
                2);
        this.waitForElementAndClick(
                MY_SAVED_LIST,
                "Cannot find Save for later button",
                2);
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot find and press X button",
                2);
    }
    public void addToMySavedAndClearInput(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Save for later button",
                2);
        this.waitForElementAndClick(
                LEAVE_ARTICLE_BUTTON,
                "Cannot find Back button",
                2);
        this.waitForElementAndClick(
                CLEAR_TEXT_BUTTON,
                "Cannot find Clear button",
                2);
    }
    public void addToMySavedAndCancel(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Save for later button",
                2);
        this.waitForElementAndClick(
                LEAVE_ARTICLE_BUTTON,
                "Cannot find Back button",
                2);
        this.waitForElementAndClick(
                CANCEL_BUTTON,
                "Cannot find Clear button",
                2);
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LISTS_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LISTS_BUTTON,
                    "Cannot click to remove article from my saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to my lists after removing it from this list before",
                    3
            );
        }
    }
}
