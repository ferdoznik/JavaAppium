package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
        SAVE_FOR_LATER = "id:Save for later";
        CLOSE_BUTTON = "id:Close";
    }
    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
