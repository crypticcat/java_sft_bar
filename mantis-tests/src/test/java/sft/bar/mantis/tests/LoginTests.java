package sft.bar.mantis.tests;

import org.testng.annotations.Test;
import sft.bar.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

   @Test
   public void testLogin() throws IOException, ServiceException {
       int issueId = 2442;
       skipIfNotFixed(issueId);
       HttpSession session = app.newSession();
       assertTrue(session.login("administrator", "root"));
       assertTrue(session.isLoggedInAs("administrator"));
   }
}
