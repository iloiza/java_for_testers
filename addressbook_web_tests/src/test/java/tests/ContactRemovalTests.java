package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void contactRemovalTest() {

        {
            if (app.contacts().isContactsPresent()) {
                app.contacts().createContacts(new ContactData("Potter", "Harry",
                        "London", "mrpotter@hw.ru", "9957774444"));
            }
            app.contacts().removeContact();

        }
    }

}
