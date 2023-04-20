package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class PlatformRIES {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_ANDROID_URL = "http://0.0.0.0:4723/wd/hub";
    private static final String APPIUM_IOS_URL = "http://0.0.0.0:4723";

    private static PlatformRIES instance;

    private PlatformRIES(){}

    public static PlatformRIES getInstance()
    {
        if(instance==null){
            instance = new PlatformRIES();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception
    {
        URL URL_ANDROID = new URL(APPIUM_ANDROID_URL);
        URL URL_IOS = new URL(APPIUM_IOS_URL);
        if (this.isAndroid()){
            return new AndroidDriver(URL_ANDROID, this.getAndroidDesiredCapabilities());
        }else if (this.isIOS()){
            return new IOSDriver(URL_IOS, this.getIOSDesiredCapabilities());
        }else throw new Exception("Cannot detect type of the driver. Platform value: " + this.getPlatformVar());
    }
    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }
    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformName", "13.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "com.riesapp");
        capabilities.setCapability("appActivity", ".features.root.RootActivity");
        capabilities.setCapability("app", "/Users/antonragozin/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/ries-ries-release.apk");
        return capabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "15.0");
        capabilities.setCapability("deviceName", "iPhone 11");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/antonragozin/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }
    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
