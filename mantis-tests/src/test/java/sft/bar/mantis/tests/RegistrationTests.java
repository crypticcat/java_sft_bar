package sft.bar.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("uesr1", "user1@localhost.localdomain");

    }
}
