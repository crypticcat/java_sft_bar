package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().createGroup(new GroupData().withName("test 0"));
        }

        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nadya").withLastname("Test")
                    .withAddress("Russia, Spb")
                    .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11").withWork("8 812 777 77 77")
                    .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                    .withGroup("test 0"));
        }
    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllPhones(), equalTo(app.contact().mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(app.contact().mergeEmails(contactInfoFromEditForm)));
    }

}
