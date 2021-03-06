package sft.bar.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import sft.bar.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    boolean isIssueOpen(int issueId) throws IOException {
        String state = app.rest().getIssueState(issueId);
        if (!state.equals("Resolved") || !state.equals("Closed")) {
            return true;
        } else {
            return false;
        }
    }

    boolean isIssueOpenSoap(int issueId) throws IOException, ServiceException {
        String state = app.soap().getIssueState(issueId);
        if (!state.equals("resolved") || !state.equals("closed")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
        if (isIssueOpenSoap(issueId)) {
            System.out.println("Ignored because of issue " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    //alwaysRun - to be sure that browser is stopped
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
    }
}
