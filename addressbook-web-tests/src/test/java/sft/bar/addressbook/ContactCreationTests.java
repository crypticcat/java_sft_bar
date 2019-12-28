package sft.bar.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() throws Exception {
        initContactCreation();
        fillContactForm(new ContactData("n5", "n5", "address5", "phone5", "email5"));
        submitContactCreation();
        returnToHomePage();
    }
}
