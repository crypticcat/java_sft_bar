package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().createGroup(new GroupData().withName("test 0"));
        }

        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nadya")
                    .withMiddlename("Middle").withLastname("Test").withNickname("Nick")
                    .withCompany("Company").withTitle("Title")
                    .withAddress("Russia, Spb")
                    .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11")
                    .withWork("8 812 777 77 77").withFax("8 812 777 77 78")
                    .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                    .withBday((byte) 11).withBmonth("January").withByear("1981")
                    .withAday((byte) 18).withAmonth("January").withAyear("2020")
                    .withGroup("test 0"));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withFirstname("Nadya")
                .withMiddlename("Middle").withLastname("Test").withNickname("Nick")
                .withCompany("Company").withTitle("Title")
                .withAddress("Russia, Spb")
                .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11")
                .withWork("8 812 777 77 77").withFax("8 812 777 77 78")
                .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                .withBday((byte) 11).withBmonth("January").withByear("1981")
                .withAday((byte) 18).withAmonth("January").withAyear("2020")
                .withGroup("test 0");
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
        verifyContactListIsInUI();
    }
}
