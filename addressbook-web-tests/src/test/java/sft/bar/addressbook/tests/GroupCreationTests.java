package sft.bar.addressbook.tests;

import org.testng.annotations.*;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("Nadya1").withHeader("header1").withFooter("footer1")});
        list.add(new Object[] {new GroupData().withName("Nadya2").withHeader("header2").withFooter("footer2")});
        list.add(new Object[] {new GroupData().withName("Nadya3").withHeader("header3").withFooter("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().createGroup(group);
        app.goTo().groupPage();
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
