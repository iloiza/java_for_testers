package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var newContact = new ContactData().
                withLastName(CommonFunctions.randomString(10)).
                withFirstName(CommonFunctions.randomString(10)).
                withPhoto(CommonFunctions.randomFile("src/test/resources/images/")).
                withHomePhone(CommonFunctions.randomNumber()).
                withMobilePhone(CommonFunctions.randomNumber()).
                withWorkPhone(CommonFunctions.randomNumber()).
                withFax(CommonFunctions.randomNumber()).
                withEmail(CommonFunctions.randomString(10) + "@email.ru");
        {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContacts(newContact);
            }
            var contacts = app.hbm().getContactList();
            var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                    Stream.of(contact.home(), contact.mobile(), contact.work())//, contact.fax())
                            .filter(s -> s != null && !"".equals(s))
                            .collect(Collectors.joining("\n"))
            ));
            var phones = app.contacts().getPhones();
            Assertions.assertEquals(expected, phones);
//            for (var contact : contacts) {
//                 var expected = Stream.of(contact.home(), contact.mobile(), contact.work())//, contact.fax())
//                        .filter(s -> s != null && !"".equals(s))
//                        .collect(Collectors.joining("\n"));
//                Assertions.assertEquals(expected, phones.get(contact.id()));
//            }

        }
    }
    @Test
    void testEmails() {
        
    }
}