package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//     for (var firstName : List.of("", "Harry")) {
//            for (var lastName : List.of("", "Potter")) {
//                result.add(new ContactData("", firstName, lastName, "", "", "", ""));
//            }
//        }
//        var mapper = new XmlMapper();
//        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
//        result.addAll(value);
//        return result;
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContacts(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.
                withId(newContacts.get(newContacts.size() - 1).id()).withEmail("").withPhoto(""));
        expectedList.sort(compareById);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(newContacts, expectedList);
        });

    }

    @Test
    public void canCreateOneContact() {
        var contact = new ContactData().
                withLastName(CommonFunctions.randomString(10)).
                withFirstName(CommonFunctions.randomString(10)).
                withPhoto(CommonFunctions.randomFile("src/test/resources/images/"));
        //D:\repo\java_for_testers\java_for_testers\addressbook_web_tests\src\test\resources\images
        app.contacts().createContacts(contact);
    }

    @Test
    public void canCreateNewContactInGroup() {
        var contact = new ContactData().
                withLastName(CommonFunctions.randomString(10)).
                withFirstName(CommonFunctions.randomString(10)).
                withPhoto(CommonFunctions.randomFile("src/test/resources/images/"));
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "Group_1", "Header_Group", "Footer_Group"));
            }
        });

        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactsInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        });
    }

    @Test
    public void canAddExistingContactInGroup() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContacts(new ContactData()
                        .withLastName(CommonFunctions.randomString(10))
                        .withFirstName(CommonFunctions.randomString(10))
                        .withPhoto(CommonFunctions.randomFile("src/test/resources/images/")));
            }
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "Group_1", "Header_Group", "Footer_Group"));
            }
        });

        List<ContactData> allContacts = app.hbm().getContactList();
        List<GroupData> allGroups = app.hbm().getGroupList();
        ContactData contactToAdd = allContacts.stream()
                .filter(contact -> !app.hbm().isContactInAnyGroup(contact))
                .findFirst()
                .orElseGet(() -> {
                    ContactData newContact = new ContactData()
                            .withLastName(CommonFunctions.randomString(10))
                            .withFirstName(CommonFunctions.randomString(10))
                            .withPhoto(CommonFunctions.randomFile("src/test/resources/images/"));
                    app.hbm().createContacts(newContact);
                    var newContacts = app.hbm().getContactList();
                    return newContact.withId(String.valueOf(newContacts.get(newContacts.size() - 1).id()));
                });

        var oldRelated = app.hbm().getContactsInGroup(allGroups.get(0));
        app.contacts().addContactsInGroup(contactToAdd, allGroups.get(0));
        var newRelated = app.hbm().getContactsInGroup(allGroups.get(0));
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        });

    }

}