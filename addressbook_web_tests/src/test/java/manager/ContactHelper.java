package manager;

import model.ContactData;
import org.openqa.selenium.By;


public class ContactHelper extends HelperBase{
    public ContactHelper(ApplicationManager manager) {
        super (manager);
    }

    public void createContacts(ContactData contact) {
        openContactCreationPage();
        fillContactForm(contact);
        submitItemCreation();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }


    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.phones());
        type(By.name("email"), contact.email());
        
    }

    private void openContactCreationPage() {
        click(By.linkText("add new"));
    }


    public boolean isContactsPresent() {
        return !manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        selectContact();
        removeSelectedContact();

    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));

    }
}
