package sft.bar.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sft.bar.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    //alwaysRun - to be sure that browser is stopped
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info(m.getName() + " starting" + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info(m.getName() + " stopped");
    }
}
