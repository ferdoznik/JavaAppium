package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase
{

    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWIKIWebPageMobileWeb();
    }

    @After
    @Step("Run Remove and session")
    public void tearDown()
    {
        driver.quit();
    }
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Restore Screen Portrait mode")
    protected void rotateScreenPortrait()
        {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
                System.out.println("Method rotateScreenPortrait does nothing for platform" + Platform.getInstance().getPlatformVar());
            }
        }

    @Step("Send mobile app to background (this method does nothing for Mobile Web)")
    protected void BackgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
            } else {
                System.out.println("Method BackgroundApp does nothing for platform" + Platform.getInstance().getPlatformVar());
            }
        }

    @Step("Open Wikipedia URL for Mobile Web (this method does nothing for Android and iOS)")
    protected void openWIKIWebPageMobileWeb()
    {
        if (Platform.getInstance().isMW())
            {driver.get("https://en.m.wikipedia.org");
            }else {
            System.out.println("Method openWIKIWebPageMobileWeb does nothing for platform" + Platform.getInstance().getPlatformVar());
            }
        }

    @Step("Skip Welcome page in iOS Wikipedia (this method does nothing for Mobile Web and Android)")
    private void skipWelcomePageForIOSApp ()
    {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        File directory = new File(path);
        if (!directory.exists()) {
            System.out.printf("Directory '%s' was not found. \n", path);
            directory.mkdir();
            System.out.printf("Created directory '%s'.\n", path);
        }
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem while writing allure properties file");
            e.printStackTrace();
        }
    }
}
