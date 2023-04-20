package lib.ui.mobile.web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[text()='Watch']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        POPOVER = "id:Tap to go back";
        LEAVE_ARTICLE_BUTTON = "id:Search";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        CLOSE_BUTTON = "id:Close";
        MY_SAVED_LIST = "id:Saved";
        CLEAR_TEXT_BUTTON = "id:Clear text";
        OPTIONS_REMOVE_FROM_MY_LISTS_BUTTON = "xpath://*[text()='Unwatch']";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
