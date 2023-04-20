package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;

public class RIESLoginPageObject extends MainPageObject
{
    private static final String
    LOGIN_FILED = "xpath://*[contains(@text,'Логин')]",
    PASSWORD_FILED = "xpath://*[contains(@text,'Пароль')]",
    RIES_LOGO = "id:com.riesapp:id/logo_view",
    EDIT_LOGIN_FILED = "xpath://*[@resource-id='com.riesapp:id/loginTextInput']//*[@resource-id='com.riesapp:id/textFieldEditText']",
    EDIT_PASSWORD_FILED = "xpath://*[@resource-id='com.riesapp:id/passwordTextInput']//*[@resource-id='com.riesapp:id/textFieldEditText']",
    RECOMMENDATION_BUTTON = "xpath://*[contains(@text,'Рекомендации')]",
    LOADER = "id:com.riesapp:id/loader",
    LOGIN_BUTTON = "id:com.riesapp:id/authButton";

    public RIESLoginPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForRIESLogoDissapear()
    {
        this.waitForElementNotPresent(RIES_LOGO,"Logo is still there",5);
    }
    public void waitForLoginButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON,"Cannot see Login button",15);
    }
    public void enterRIESLogin()
    {
        this.waitForElementPresent(LOGIN_FILED, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(LOGIN_FILED, "Cannot find and click search init element", 2);
        this.waitForElementAndSendKeys(EDIT_LOGIN_FILED,"18858","Cannot see and send login", 5);
    }
    public void enterRIESPassword()
    {
        this.waitForElementPresent(PASSWORD_FILED, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(PASSWORD_FILED, "Cannot find and click search init element", 2);
        this.waitForElementAndSendKeys(EDIT_PASSWORD_FILED,"Test20231","Cannot see and send login", 5);
    }
    public void clickLOGINButton()
    {
        this.waitForElementAndClick(LOGIN_BUTTON,"Cannot find and click LOGIN button",10);
        this.waitForElementNotPresent(LOADER, "Can see Loader", 10);
    }

    public void waitForLoaderToDisappear()
    {
        this.waitForElementNotPresent(LOADER, "Can see Loader", 10);
    }
    public void assertIfBlockIsThere()
    {
        this.assertElementIsPresent(RECOMMENDATION_BUTTON, "Cannot find Recommendations");
    }
}
