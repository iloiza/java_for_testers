package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void contactRemovalTest() {

        {
            if (app.contacts().isContactsPresent()) {
                app.contacts().createContacts(new ContactData("", "Potter", "Harry",
                        "London", "mrpotter@hw.ru", "9957774444", ""));
            }
            var oldContacts = app.contacts().getList();
            var rnd = new Random();
            var index = rnd.nextInt(oldContacts.size());
            app.contacts().removeContact(oldContacts.get(index));
            var newContacts = app.contacts().getList();
            var expectedList = new ArrayList<>(oldContacts);
            expectedList.remove(index);
            Assertions.assertEquals(expectedList, newContacts);

        }
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        {
            if (app.contacts().isContactsPresent()) {
                app.contacts().createContacts(new ContactData("", "Potter", "Harry",
                        "London", "mrpotter@hw.ru", "9957774444", ""));
            }
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
