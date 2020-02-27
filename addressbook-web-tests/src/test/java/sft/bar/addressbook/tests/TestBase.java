package sft.bar.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sft.bar.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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


    public void verifyGroupListIsInUI() {
        if (Boolean.getBoolean("verifyUI")) {

        }
        Groups dbGroups = app.db().groups();
        Groups uiGroups = app.group().all();
        assertThat(uiGroups, equalTo(dbGroups.stream()
                .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                .collect(Collectors.toSet())));
    }

    public void verifyContactListIsInUI() {
        if (Boolean.getBoolean("verifyUI")) {

        }
        Contacts dbContacts = app.db().contacts();
        Contacts uiContacts = app.contact().all();
        assertThat(uiContacts, equalTo(dbContacts.stream()
                .map((g) -> new ContactData().withId(g.getId())
                        .withFirstname(g.getFirstname()).withLastname(g.getLastname())
                        .withAddress(g.getAddress()))
                .collect(Collectors.toSet())));
    }
}
