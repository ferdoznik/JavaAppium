package lib.ui.mobile.web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
        SAVE_FOR_LATER = "id:Save for later";
        CLOSE_BUTTON = "id:Close";
    }
    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}