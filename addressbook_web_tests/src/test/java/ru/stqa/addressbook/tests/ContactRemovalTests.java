package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;


public class ContactRemovalTests extends TestBase {

    @Test
    public void contactRemovalTest() {
        var contact = new ContactData().
                withLastName(CommonFunctions.randomString(10)).
                withFirstName(CommonFunctions.randomString(10)).
                withPhoto(CommonFunctions.randomFile("src/test/resources/images/"));
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContacts(contact);
            }
        });

        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(expectedList, newContacts);
        });


    }

    @Test
    public void removeContactFromGroupTest() throws IllegalStateException {
        Allure.step("Checking precondition", step -> {

            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "Group_1", "Header_Group", "Footer_Group"));
            }
        });
        var groups = app.hbm().getGroupList();
        GroupData group = new GroupData();
        for (GroupData x : groups) {
            group = x;
            if (!app.hbm().getContactsInGroup(group).isEmpty()) break;
        }
        if (app.hbm().getContactsInGroup(group).isEmpty()) {
            app.contacts().createContactsInGroup(new ContactData("", "Potter", "Harry",
                    "London", "mrpotter@hw.ru", "", "", CommonFunctions.randomFile("src/test/resources/images/"), "9957774444", "", "", ""), group);
        }
        var essentialGroup = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(essentialGroup);
        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());
        app.contacts().removeContactFromGroup(oldRelated.get(index), essentialGroup);
        var newRelated = app.hbm().getContactsInGroup(essentialGroup);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
        });


    }
}


