package sft.bar.addressbook.tests;

import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("n5", "n5", "address5", "phone5", "email5"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
        app.getSessionHelper().logout();
    }
}
