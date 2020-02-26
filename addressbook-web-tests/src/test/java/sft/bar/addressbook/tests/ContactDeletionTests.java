package sft.bar.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nadya")
                    .withMiddlename("Middle").withLastname("Test").withNickname("Nick")
                    .withCompany("Company").withTitle("Title")
                    .withAddress("Russia, Spb")
                    .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11")
                    .withWork("8 812 777 77 77").withFax("8 812 777 77 78")
                    .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                    .withBday((byte) 12).withBmonth("January").withByear("1981")
                    .withAday((byte) 18).withAmonth("January").withAyear("2020")
                    .withGroup("test 0"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
