package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.Contacts;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase{

    @BeforeMethod
    //preconditions - if you selected a contact, check if it is added to all groups
    //and in this case you should select other contact
    //or create one more group to which you can add the contact
    //compare the lists from bd??: the groups of the contact and the list of all available groups
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
    public void testContactAddToGroup () {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        Groups contactGroupsBefore = contact.getGroups();
        System.out.println("before: " + contactGroupsBefore);
        app.contact().addToGroup(contact, group);
        app.goTo().singleGroupPageByLink();
        Groups contactGroupsAfter = contact.getGroups();
        System.out.println("after: " + contactGroupsAfter);
        assertThat(contactGroupsAfter.size(), equalTo(contactGroupsBefore.size() + 1));
        //assertThat(after, equalTo(
         //     before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
