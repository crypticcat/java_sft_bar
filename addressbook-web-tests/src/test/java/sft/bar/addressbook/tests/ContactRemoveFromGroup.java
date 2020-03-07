package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import java.util.stream.Collectors;

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
        GroupData group = chooseGroup();
        logger.info("Group:" + group);
        int groupId = group.getId();

        Contacts groupContactsBefore = group.getContacts();
        logger.info("Contacts before: " + groupContactsBefore);
        ContactData contact = groupContactsBefore.stream().findAny().get();
        logger.info("Contact to remove:" + contact);

        app.goTo().singleGroupPage(groupId);
        app.contact().removeFromGroup(contact);
        app.goTo().singleGroupPageByLink();

        GroupData groupWithRemoved = app.db().groups2().stream()
                .filter(c -> app.db().groups2().contains(c.withId(groupId)))
                .collect(Collectors.toList()).get(0);
        Contacts groupContactsAfter = groupWithRemoved.getContacts();
        logger.info("Contacts after: " + groupContactsAfter);
        assertThat(groupContactsAfter.size(), equalTo(groupContactsBefore.size() - 1));
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(contact)));
    }
}
