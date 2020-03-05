package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if  (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test 0"));
        }

        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nadya")
                    .withMiddlename("Middle").withLastname("Test").withNickname("Nick")
                    .withCompany("Company").withTitle("Title")
                    .withAddress("Russia, Spb")
                    .withHome("8(812)111-11-11").withMobile("+7(900)111-11-11")
                    .withWork("8 812 777 77 77").withFax("8 812 777 77 78")
                    .withEmail("email@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                    .withBday((byte) 12).withBmonth("January").withByear("1981")
                    .withAday((byte) 18).withAmonth("January").withAyear("2020"));
        }
    }

    @Test
    public void testContactRemoveFromGroup () {
        app.goTo().groupPage();
        GroupData group = app.db().groups().iterator().next();
        ContactData contact = checkContactsInGroup(group);
        Contacts groupContactsBefore = group.getContacts();//why null, if it was added?
        System.out.println("before: " + groupContactsBefore);
        app.contact().removeFromGroup(contact);
        app.goTo().singleGroupPageByLink();
        Contacts groupContactsAfter = group.getContacts();
        System.out.println("after: " + groupContactsAfter);
        assertThat(groupContactsAfter.size(), equalTo(groupContactsBefore.size() - 1));
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(contact)));
    }

    public ContactData checkContactsInGroup(GroupData group) {
        ContactData contact = null;
        if (group.getContacts().size() > 1) {
            contact = group.getContacts().iterator().next();
            app.goTo().singleGroupPage(group.getId());//could it bring some problems?
        } else if (group.getContacts().size() == 0) {
            app.goTo().homePageFromSingleGroupPage();
            //ContactData contactToAdd = app.db().contacts().iterator().next();
            // а если один контакт в базе?? см как делала по аналогии
            app.contact().addToGroup(contactToAdd, group);
            //group.getContacts().stream().filter((c) -> c.getId()).collect(Collectors.toList());
                    //
            // users.stream()
            //        .filter(user -> user.getId() > 0)
            //        .collect(toSingleton());.
            //        findFirst().orElse(null);
            //group.getContacts().forEach (e ->  //);
        } else {
            group.getContacts().forEach (e ->  //);
        }
        return contact;
    }
}
