package sft.bar.addressbook.tests;

import org.testng.annotations.*;
import sft.bar.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation("new");
        app.getGroupHelper().fillGroupForm(new GroupData("NadyaTest", "NadyaTest1", "NadyaTest2"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        app.getSessionHelper().logout();
    }

}
