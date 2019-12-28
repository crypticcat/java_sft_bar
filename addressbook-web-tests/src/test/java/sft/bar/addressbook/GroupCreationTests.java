package sft.bar.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests {

    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {

        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.26.0-win64\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation("new");
        fillGroupForm(new GroupData("NadyaTest", "NadyaTest1", "NadyaTest2"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void returnToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    private void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Groups'])[1]/following::form[1]")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    private void initGroupCreation(String s) {
        wd.findElement(By.name(s)).click();
    }

    private void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
