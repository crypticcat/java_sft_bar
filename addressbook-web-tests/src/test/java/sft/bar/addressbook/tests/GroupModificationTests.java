package sft.bar.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().createGroup(new GroupData().withName("test 0"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        //выбирается группа случайным образом
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("NadyaTest").withHeader("NadyaTest1").withFooter("NadyaTest2");

        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
