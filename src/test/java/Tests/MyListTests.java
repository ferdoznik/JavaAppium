package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

public class MyListTests extends CoreTestCase
{
    private static final String
    login = "Korsak1",
    password = "korsak0000";
    private static final String name_of_folder = "Java (programming language)";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"),  @Feature(value = "MyLists")})
    @DisplayName("Save the first found article to my new list")
    @Description("Add the first found article to the new reading list and then remove it from the list")
    @Step("Starting test 'testSaveFirstArticleToMyList'")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
        } else {
            SearchPageObject.clickAddToWatchList();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            String url = driver.getCurrentUrl();
            String new_url = url.substring(0, 11) + "m." + url.substring(11);
            driver.get(new_url);

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.holdArticleToDelete(article_title);
            MyListsPageObject.deleteSavedArticle();
        } else {
            MyListsPageObject.swipeByArticleToDelete(article_title);
        }
    }
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"),  @Feature(value = "MyLists")})
    @DisplayName("Delete one of two articles from the reading list")
    @Description("Add two articles to the reading list and then check remove one article from two saved ones")
    @Step("Starting test 'testSaveTwoArticles'")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveTwoArticles() // Ex17: Рефакторинг
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
//        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            System.out.println("AUTH SUCCESS");

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    first_article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Swift");
        SearchPageObject.clickByArticleWithSubstring("programming language");

        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        List<String> ArticlesBeforeDelete = MyListsPageObject.addArticlesToList();

        System.out.println(ArticlesBeforeDelete.size());

        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitForArticleToDisappearByTitle(first_article_title);

        System.out.println("The first article was deleted");

        List<String> ArticlesAfterDelete = MyListsPageObject.addArticlesToList();

        Assert.assertTrue("Articles do not match",
                ArticlesBeforeDelete.get(0).equals(ArticlesAfterDelete.get(0)));

        Assert.assertTrue("It seems that we don't delete an article",
                ArticlesAfterDelete.size() == 1 && ArticlesBeforeDelete.size() == 2);

    }
}