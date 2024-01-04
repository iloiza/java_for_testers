package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new GroupData("Group 1", "Header Group", "Footer Group"));
        }
        app.groups().modifyGroup(new GroupData().withName("modifyedName"));
    }
}
