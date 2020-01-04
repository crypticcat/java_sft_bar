package sft.bar.addressbook.tests;

import org.testng.annotations.*;
import sft.bar.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("null", "NadyaTest1", "null"));
    }

}
