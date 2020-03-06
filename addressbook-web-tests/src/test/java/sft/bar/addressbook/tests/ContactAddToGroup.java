package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.not;
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
        Groups totalGroups = app.db().groups();

        app.goTo().homePage();
        ContactData contact = app.db().contacts().stream().findAny().get();
        logger.info("Contact:" + contact);
        createNewGroupIfHasAllGroups(contact);
        Groups contactGroupsBefore = contact.getGroups();
        GroupData group = totalGroups.stream()
                .filter((c -> !contact.getGroups().contains(c)))
                .collect(Collectors.toList()).get((int) Math.random());
        logger.info("Group to add:" + group);
        logger.info("Groups before: " + contactGroupsBefore);
        app.contact().addToGroup(contact, group);
        app.goTo().singleGroupPageByLink();
        Groups contactGroupsAfter = contact.getGroups();
        logger.info("Groups after: " + contactGroupsAfter);
        assertThat(contactGroupsAfter.size(), equalTo(contactGroupsBefore.size() + 1));
        assertThat(contactGroupsAfter, equalTo(
                contactGroupsBefore.withAdded(group.withId(contactGroupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
