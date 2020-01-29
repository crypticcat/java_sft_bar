package sft.bar.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"n6", "n5", "address5", "phone5", "email5", null);
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
