package sft.bar.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("n5").withLastname("n5").withAddress("address5").withMobile("phone5").withEmail("email5").withGroup("NadyaTest"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("n5").withLastname("n5").withAddress("address5").withMobile("phone5").withEmail("email5").withGroup("NadyaTest");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
