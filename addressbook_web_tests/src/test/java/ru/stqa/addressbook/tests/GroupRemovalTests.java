package ru.stqa.addressbook.tests;// Generated by Selenium IDE

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void groupRemovalTest() {
        {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "Group_1", "Header_Group", "Footer_Group"));
            }
            var oldGroups = app.hbm().getGroupList();
            var rnd = new Random();
            var index = rnd.nextInt(oldGroups.size());
            app.groups().removeGroup(oldGroups.get(index));
            var newGroups = app.hbm().getGroupList();
            var expectedList = new ArrayList<>(oldGroups);
            expectedList.remove(index);
            Assertions.assertEquals(newGroups, expectedList);

        }
    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "Group_1", "Header_Group", "Footer_Group"));
            }
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());

    }
}
