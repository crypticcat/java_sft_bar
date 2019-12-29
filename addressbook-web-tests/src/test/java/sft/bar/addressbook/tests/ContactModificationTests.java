package sft.bar.addressbook.tests;

import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("n5", "n5", "address5", "phone5", "email5"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        app.getSessionHelper().logout();

    }
}
