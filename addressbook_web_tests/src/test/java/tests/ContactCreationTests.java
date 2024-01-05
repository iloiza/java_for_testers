package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{
    @Test
    public void createNewContact() {
        app.contacts().createContacts(new ContactData("Smith", "Sam",
                "New York", "mrsmith@ya.ru", "8957774444"));

    }

    @Test
    public void createNewContactWithLastNameOnly() {
        app.contacts().createContacts(new ContactData().withLastName("Potter"));
    }
}
