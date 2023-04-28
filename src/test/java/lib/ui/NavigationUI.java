package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
            MY_LISTS_LINK,
            CLOSE_BUTTON,
            OPEN_NAVIGATION,
            SAVE_FOR_LATER;


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Opening the page with saved articles/reading lists")
    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()){
            this.waitForElementPresent(
                    MY_LISTS_LINK,
                    "Cannot see Watchlist",
                    10
            );
            this.tryClickElementWithSeveralAttempts(
                    MY_LISTS_LINK,
                    "Cannot find and click open navigation",
                    10
            );
        }
//        this.waitForElementAndClick(
//                MY_LISTS_LINK,
//                "Cannot find My lists",
//                5);
    }

    @Step("Clicking save and closing Mu lists")
    public void clickSavedAndClose()
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot click Save for later",
                5
        );
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot find and press X button",
                2);
    }

    @Step("Opening the article main menu")
    public void openNavigation() {
        if(Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation", 5);
        } else {
            System.out.println("Method openNavigation does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Adding to Favourites by clicking the Star")
    public void clickStarInMyLists(){
        if(Platform.getInstance().isMW()) {
            this.waitForElementAndClick(MY_LISTS_LINK, "Cannot find and click open navigation", 5);
        } else {
            System.out.println("Method openNavigation does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
