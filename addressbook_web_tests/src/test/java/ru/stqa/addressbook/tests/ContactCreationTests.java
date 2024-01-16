package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstName : List.of("", "Harry")) {
//            for (var lastName : List.of("", "Potter")) {
//                result.add(new ContactData("", firstName, lastName, "", "", "", ""));
//            }
//        }
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContacts(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.
                withId(newContacts.get(newContacts.size() - 1).id()).
                withAddress("").
                withEmail("").
                withPhones("").
                withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }

    @Test
    public void canCreateOneContact() {
        var contact = new ContactData().
                withLastName(CommonFunctions.randomString(10)).
                withFirstName(CommonFunctions.randomString(10)).
                withPhoto(CommonFunctions.randomFile("addressbook_web_tests/src/test/resources/images/"));
        //D:\repo\java_for_testers\java_for_testers\addressbook_web_tests\src\test\resources\images
        app.contacts().createContacts(contact);
    }

}
