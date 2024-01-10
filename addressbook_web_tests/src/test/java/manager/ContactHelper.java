package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
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

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContact();
    }

    private void openContactCreationPage() {
        click(By.linkText("add new"));
    }


    public boolean isContactsPresent() {
        return !manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContact();
        manager.driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
        //click(By.name("selected[]"));

    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }


    private void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var td = manager.driver.findElements(By.name("entry"));
        for (var entry : td){
            var className = entry.findElement(By.cssSelector("td.center"));
            var titleName = entry.findElement(By.cssSelector("td.center")).getAttribute("title");
            var checkbox = className.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(titleName));
        }
        return contacts;    }
}
