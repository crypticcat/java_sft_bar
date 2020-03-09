package sft.bar.addressbook.tests;

import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends ContactAddToGroup {

    @Test(dependsOnMethods = { "testContactAddToGroup" })
    public void testContactRemoveFromGroup () {
        GroupData group = app.db().groups1().stream().findAny().get();
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
