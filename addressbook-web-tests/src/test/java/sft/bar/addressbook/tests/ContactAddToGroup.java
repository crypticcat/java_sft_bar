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

public class ContactAddToGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        createIfNoContacts();
        createIfNoGroups();
    }

    @Test
    public void testContactAddToGroup () {
        ContactData contact = app.db().contacts1().stream().findAny().get();
        createGroupIfHasAllGroups(contact);
        Groups contactGroupsBefore = contact.getGroups();
        logger.info("Contact:" + contact);
        GroupData group = app.db().groups1().stream()
                .filter((c -> !contact.getGroups().contains(c)))
                .collect(Collectors.toList()).get((int) Math.random());
        int contactId = contact.getId();
        logger.info("Group to add:" + group);
        logger.info("Groups before: " + contactGroupsBefore);

        app.goTo().homePage();
        app.contact().addToGroup(contact, group);
        app.goTo().singleGroupPageByLink();

        ContactData contactWithAdded = app.db().contacts2().stream()
                .filter(c -> app.db().contacts2().contains(c.withId(contactId)))
                .collect(Collectors.toList()).get(0);
        Groups contactGroupsAfter = contactWithAdded.getGroups();
        logger.info("Groups after: " + contactGroupsAfter);
        assertThat(contactGroupsAfter.size(), equalTo(contactGroupsBefore.size() + 1));
        assertThat(contactGroupsAfter, equalTo(
                contactGroupsBefore.withAdded(group.withId(contactGroupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
