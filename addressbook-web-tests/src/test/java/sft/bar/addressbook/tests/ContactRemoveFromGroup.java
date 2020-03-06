package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        createIfNoContacts();
        createIfNoGroups();
    }

    @Test
    public void testContactRemoveFromGroup () {
        GroupData group = app.db().groups().stream().findAny().get();
        logger.info("Group:" + group);
        addIfNoContactsInGroup(group);
        Contacts groupContactsBefore = group.getContacts();
        logger.info("Contacts before: " + groupContactsBefore);
        ContactData contact = groupContactsBefore.stream().findAny().get();
        logger.info("Contact to remove:" + contact);
        app.goTo().singleGroupPage(group.getId());
        app.contact().removeFromGroup(contact);
        app.goTo().singleGroupPageByLink();
        Contacts groupContactsAfter = group.getContacts();
        logger.info("Contacts after: " + groupContactsAfter);
        assertThat(groupContactsAfter.size(), equalTo(groupContactsBefore.size() - 1));
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(contact)));
    }
}
