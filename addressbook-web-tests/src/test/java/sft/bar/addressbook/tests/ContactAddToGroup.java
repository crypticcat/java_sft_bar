package sft.bar.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if  (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test 0"));
        }

    }

    @Test
    public void testContactAddToGroup () {
        app.goTo().homePage();
        ContactData contact = returnContact();
        checkContactHasAllGroups(contact);
        GroupData group = returnGroup();
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

    public ContactData returnContact() {
        ContactData contact = null;
        if (app.db().contacts().size() > 1) {
            contact = app.db().contacts().iterator().next();
        } else if (app.db().contacts().size() == 1) {
            contact = app.db().contacts().Iterable.get();
        } else (app.db().contacts().size() == 0) {
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

        return contact;
    }


    //и если еще группа уже есть такая у контакта, то что сделать?
    public GroupData returnGroup() {
        GroupData group = null;
        if (app.db().contacts().size() > 1) {
            group = app.db().groups().iterator().next();
        } else if (app.db().groups().size() == 1) {
            //app.db().contacts().forEach(e -> //);
        }
        return group;
    }


    public void checkContactGroups(ContactData contact) {
        if (contact.getGroups().size() == app.db().groups().size()) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test 0"));
        } else if (//contact is added t group already)
    }
}
