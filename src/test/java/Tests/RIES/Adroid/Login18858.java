package Tests.RIES.Adroid;

import lib.CoreTestCaseRIES;
import lib.ui.RIESLoginPageObject;
import lib.ui.RIESMainPageObject;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login18858 extends CoreTestCaseRIES
{
    @Test
    public void testLogin18858()
    {
//        if (PlatformRIES.getInstance().isAndroid()){
//            return;
//        }
        RIESLoginPageObject RIESLogin = new RIESLoginPageObject(driver);
        RIESLogin.waitForLoginButton();
        RIESLogin.enterRIESLogin();
        RIESLogin.enterRIESPassword();
        RIESLogin.clickLOGINButton();
        RIESLogin.waitForLoaderToDisappear();
        RIESLogin.assertIfBlockIsThere();
    }
}
