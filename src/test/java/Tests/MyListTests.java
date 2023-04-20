package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import javax.swing.*;

public class MyListTests extends CoreTestCase
{
    private static final String
    login = "Korsak1",
    password = "korsak0000";
    private static final String name_of_folder = "Java (programming language)";
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }if (Platform.getInstance().isMW()){
        AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
        Auth.clickAuthButton();
        Auth.enterLoginData(login, password);
        Auth.submitForm();

        ArticlePageObject.waitForTitleElement();
        assertEquals("We are not on the same page after login",
                article_title,
                ArticlePageObject.getArticleTitle());

        ArticlePageObject.addArticlesToMySaved();
    }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
//        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.holdArticleToDelete(article_title);
        MyListsPageObject.deleteSavedArticle();
        SearchPageObject.checkIfArticleIsDeleted();
    }
}
