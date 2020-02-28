package sft.bar.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class ManageUsersHelper extends HelperBase{

    public ManageUsersHelper(ApplicationManager app) {
        super(app);
    }

    public void adminLogin() throws IOException {
        wd.get(app.getProperty("web.baseUrl"));
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys(app.getProperty("web.adminLogin"));
        wd.findElement(By.id("login-form")).submit();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(app.getProperty("web.adminPass"));
        wd.findElement(By.id("login-form")).submit();
    }

    public void resetPassword(String user) {
        wd.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i")).click();
        wd.findElement(By.linkText("Manage Users")).click();
        wd.findElement(By.linkText(user)).click();
        wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
    }

    public void updatePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span"));
    }

}
