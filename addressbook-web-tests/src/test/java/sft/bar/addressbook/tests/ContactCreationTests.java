package sft.bar.addressbook.tests;

import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("n5").withLastname("n5").withAddress("address5").withMobile("phone5").withEmail("email5").withGroup("NadyaTest");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
