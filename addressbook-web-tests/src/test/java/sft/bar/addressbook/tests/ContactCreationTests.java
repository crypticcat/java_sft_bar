package sft.bar.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("n5", "n5", "address5", "phone5", "email5", "NadyaTest"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
