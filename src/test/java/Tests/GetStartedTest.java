package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    @Feature(value = "WelcomePage")
    @DisplayName("Check the welcome page")
    @Description("We check links displayed on the welcome page")
    @Step("Starting test 'testPassThroughWelcome'")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysToExplore();
        WelcomePage.clickNextButtonSecondTime();

        WelcomePage.waitForSearchInNearly300Languages();
        WelcomePage.clickNextButtonThirdTime();

        WelcomePage.clickGetStarted();
    }
}
