package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class HW_L7 extends CoreTestCase
{
    private static final String
            login = "Korsak1",
            password = "korsak0000";

    private static final String name_of_folder = "Video game series and multimedia franchise";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Mortal Kombat");
        SearchPageObject.clickByArticleWithSubstring("Video game series and multimedia franchise");

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

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Tekken");
            SearchPageObject.clickByArticleWithSubstring("Fighting video game series");

            ArticlePageObject.waitForTitleElement();

            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.addArticleToMyList(name_of_folder);
            } else if (Platform.getInstance().isIOS()) {
                ArticlePageObject.addToMySavedAndCancel(name_of_folder);
            } else {
                NavigationUI NavigationUI = NavigationUIFactory.get(driver);
                NavigationUI.openNavigation();
                NavigationUI.clickMyLists();
            }

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
    }
}
