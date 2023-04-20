package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME_TPL,
        MY_SAVED_ARTICLE,
        REMOVE_FROM_SAVED_BUTTON,
        REMOVE_ARTICLE_FROM_SAVED,
        ARTICLE_BY_TITLE_TPL;
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find created folder" + name_of_folder,
                10
        );
    }
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title" + article_title,
                10
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title" + article_title,
                15
        );
    }
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

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
    public void holdArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.tapElementAndHold(article_xpath,
                "Cannot see the article",
                6000);
    }
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
}