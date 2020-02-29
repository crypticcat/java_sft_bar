package sft.bar.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import sft.bar.mantis.model.MailMessage;
import sft.bar.mantis.model.UserData;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException {
        List<UserData> users = app.db().users();
        UserData randomUser = users.get(new Random().nextInt(users.size()));
        String userLogin = randomUser.getUsername();
        String email = randomUser.getEmail();
        String password = "pass" + System.currentTimeMillis();

        app.manage().adminLogin();
        app.manage().resetPassword(userLogin);
        //2. An email is sent to the user's email account, the tests should get this email,
        // extract the confirmation link, follow this link and update password.
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String verifyLink = findVerifyLink(mailMessages, email);
        app.manage().updatePassword(verifyLink, password);
        //3. Check if the user is able to login with the new password.
        assertTrue(app.newSession().login(userLogin, password));
    }

    private String findVerifyLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
