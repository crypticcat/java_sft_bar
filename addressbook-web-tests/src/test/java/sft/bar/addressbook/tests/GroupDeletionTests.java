package sft.bar.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sft.bar.addressbook.model.GroupData;
import sft.bar.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().createGroup(new GroupData().withName("test 0"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.db().groups();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}
