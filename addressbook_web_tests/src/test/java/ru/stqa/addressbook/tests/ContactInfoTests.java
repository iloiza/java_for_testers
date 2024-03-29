package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.manager.HelperBase;
import ru.stqa.addressbook.model.ContactData;

import java.util.Random;
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
                withFax("").
                withEmail2("").
                withEmail3("").
                withEmail(CommonFunctions.randomString(10) + "@email.ru");
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContacts(newContact);
            }
        });

        new HelperBase(app).openHomePage();
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work())//, contact.fax())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(expected, phones);
        });


    }

    @Test
    void checkContactsAddressPhonesAndEmails() {
        var newContact = new ContactData().
                withLastName(CommonFunctions.randomString(10)).
                withFirstName(CommonFunctions.randomString(10)).
                withPhoto(CommonFunctions.randomFile("src/test/resources/images/")).
                withHomePhone(CommonFunctions.randomNumber()).
                withMobilePhone(CommonFunctions.randomNumber()).
                withWorkPhone(CommonFunctions.randomNumber()).
                withFax("").
                withEmail2("").
                withEmail3("").
                withEmail(CommonFunctions.randomString(10) + "@email.ru");
        Allure.step("Checking precondition", step -> {

            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContacts(newContact);
            }
        });

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contactInfoMain = app.contacts().getContactInfoFromMainPage(contacts.get(index).withFax(""));
        var contactInfoEdit = app.contacts().getContactInfoFromEditPage(contacts.get(index).withFax(""));
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(contactInfoMain, contactInfoEdit);
        });
    }
}
