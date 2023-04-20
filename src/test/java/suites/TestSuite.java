package suites;

import Tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionsTests.class,
        GetStartedTest.class,
        MyListTests.class,
        SearchTests.class

})
public class TestSuite {}
