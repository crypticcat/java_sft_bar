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
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withFirstname("Nadya").withLastname("TestM")
                .withAddress("Russia, Spb")
                .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11").withWork("8 812 777 77 77")
                .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                .withGroup("test 0");
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getFirstname(), equalTo(contactInfoFromEditForm.getFirstname()));
        assertThat(contact.getLastname(), equalTo(contactInfoFromEditForm.getLastname()));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(app.contact().mergePhones(contact), equalTo(app.contact().mergePhones(contactInfoFromEditForm)));
        assertThat(app.contact().mergeEmails(contact), equalTo(app.contact().mergeEmails(contactInfoFromEditForm)));
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
