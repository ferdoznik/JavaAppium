package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
    STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAYS_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
    STEP_SEARCH_300_LANG = "id:Search in over 300 languages",
    NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']",
    GET_STARTED = "xpath://XCUIElementTypeButton[@name='Get started']",
    SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";
    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for the link 'Learn more about Wikipedia'")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Dont see the welcome link",5);
    }

    @Step("Clicking the button 'Next'")
    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Cannot find and click Next button",5);
    }

    @Step("Waiting for the link 'New ways to explore'")
    public void waitForNewWaysToExplore()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,"Dont see New ways to explore",5);
    }

    @Step("Clicking the button 'Next'")
    public void clickNextButtonSecondTime()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Cannot find and click Next button",5);
    }

    @Step("Waiting for the link 'Add or edit preferred languages'")
    public void waitForSearchInNearly300Languages()
    {
        this.waitForElementPresent(STEP_SEARCH_300_LANG,"Dont see Search in nearly 300 languages link",5);
    }

    @Step("Clicking the button 'Next'")
    public void clickNextButtonThirdTime()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Cannot find and click Next button",5);
    }

    @Step("Clicking the button 'Get started'")
    public void clickGetStarted()
    {
        this.waitForElementAndClick(GET_STARTED,"Cannot find and click Get started button",5);
    }

    @Step("Clicking the button 'Skip'")
    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find and click Skip button", 5);
    }
}
