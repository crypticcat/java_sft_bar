package sft.bar.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation("new");
        fillGroupForm(new GroupData("NadyaTest", "NadyaTest1", "NadyaTest2"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }

}
