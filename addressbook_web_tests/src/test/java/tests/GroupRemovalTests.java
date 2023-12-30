package tests;// Generated by Selenium IDE

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void groupRemovalTest() {
        {
            if (app.groups().isGroupPresent()) {
                app.groups().createGroup(new GroupData("Group_1", "Header_Group", "Footer_Group"));
            }
            app.groups().removeGroup();

        }
    }


}