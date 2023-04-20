package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject
{
    static {
//        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
            ARTICLE_BY_TITLE_TPL = "id:Fighting video game series";
            MY_SAVED_ARTICLE = "id:Java (programming language)";
            REMOVE_ARTICLE_FROM_SAVED = "id:Remove from saved";
    }
    public iOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}

