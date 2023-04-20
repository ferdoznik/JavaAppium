package lib.ui.mobile.web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "xpath://*[text()='Watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        SAVE_FOR_LATER = "id:Save for later";
        CLOSE_BUTTON = "id:Close";

    }
    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}