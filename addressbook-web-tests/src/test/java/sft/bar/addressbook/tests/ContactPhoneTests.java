package sft.bar.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static sft.bar.addressbook.tests.TestBase.app;

public class ContactPhoneTests {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("n5").withLastname("n5").withAddress("address5").withMobile("phone5").withEmail("email5").withGroup("NadyaTest"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
