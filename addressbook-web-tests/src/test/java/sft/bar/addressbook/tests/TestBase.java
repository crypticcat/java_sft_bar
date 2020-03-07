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
import java.security.acl.Group;
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


    public void createIfNoContacts() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nadya")
                    .withMiddlename("Middle").withLastname("Test").withNickname("Nick")
                    .withCompany("Company").withTitle("Title")
                    .withAddress("Russia, Spb")
                    .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11")
                    .withWork("8 812 777 77 77").withFax("8 812 777 77 78")
                    .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                    .withBday((byte) 12).withBmonth("January").withByear("1981")
                    .withAday((byte) 18).withAmonth("January").withAyear("2020"));
        }
    }

    public void createIfNoGroups() {
            if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test 0"));
        }
    }

    public void createGroupIfHasAllGroups(ContactData contact) {
        if (contact.getGroups().size() == app.db().groups().size()) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test 0"));
        }
    }

    public GroupData chooseGroup() {
        GroupData group = app.db().groups1().stream().findAny().get();
        if (group.getContacts().size() > 0) {
        } else if (group.getContacts().size() == 0) {
            //contact.
            //app.contact().addToGroup(contact, group);
            //Groups contactGroupsAfter = contacts2.stream()
            //        .filter(c -> contacts2.contains(c.withId(id))).findAny().get().getGroups();
            //ContactData groupWithAdded = app.db().groups1.stream().findFirst().get();
            //return groupWithAdded;
        }
        return group;
    }
}
