package sft.bar.addressbook.tests;

import org.testng.annotations.Test;
import sft.bar.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("n5", "n5", "address5", "phone5", "email5", "NadyaTest"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }

}
