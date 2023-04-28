package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionsTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "AppCondition")})
    @DisplayName("Check search results after sending the app to the background")
    @Description("Search for the article, then send the currently active app to the background, return after 2 sec and check search results")
    @Step("Starting test 'testCheckArticleInBackground'")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckArticleInBackground()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.BackgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "AppCondition")})
    @DisplayName("Check search results by setting different device orientations")
    @Description("Search for the article, rotate the device and check search results, then return device back to the portrait orientation and check them again")
    @Step("Starting test 'testChangeScreenOrientation'")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testChangeScreenOrientation() {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_Rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_Rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_Rotation,
                title_after_Rotation
        );
        this.rotateScreenLandscape();
        String title_after_second_Rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_Rotation,
                title_after_second_Rotation
        );
    }
}
