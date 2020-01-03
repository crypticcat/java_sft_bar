package sft.bar.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sft.bar.addressbook.appmanager.ApplicationManager;
import sun.plugin2.util.BrowserType;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.DEFAULT);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
