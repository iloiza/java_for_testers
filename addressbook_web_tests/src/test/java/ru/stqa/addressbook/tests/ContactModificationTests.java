package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact(){
        Allure.step("Checking precondition", step -> {
            {
                if (app.contacts().isContactsPresent()) {
                    app.contacts().createContacts(new ContactData("", "Potter", "Harry",
                            "London", "mrpotter@hw.ru", "", "", CommonFunctions.randomFile("src/test/resources/images/"), "", "", "", ""));
                }
            }
        });

        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        ContactData testData = new ContactData().withFirstName("ModifyContactName")
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images/"));
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()).withPhoto("").withFax(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(expectedList, newContacts);
        });
    }
}
