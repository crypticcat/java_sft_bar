package sft.bar.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent((By.name("new")))
        ) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void singleGroupPage(int id) {
        wd.get(String.format("http://localhost/?group=%s", id));
    }

    public void singleGroupPageByLink() {
        wd.findElement(By.xpath("//*[@id=\"content\"]/div/i/a")).click();
    }

    public void homePageFromSingleGroupPage() {
        wd.get("http://localhost/");
    }
}
