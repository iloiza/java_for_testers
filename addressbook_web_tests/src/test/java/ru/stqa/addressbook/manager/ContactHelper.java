package ru.stqa.addressbook.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public void createContactsInGroup(ContactData contact, GroupData group) {
        openContactCreationPage();
        fillContactForm(contact);
        selectGroup(group);
        submitItemCreation();
        waiting();
        returnToHomePage();
    }

    public void addContactsInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupForAdding(group);
        submitAddingContactInGroup();
    }

    private void submitAddingContactInGroup() {
        click(By.name("add"));
    }

    private void selectGroupForAdding(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());

    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {

        selectGroupWithContactList(group);
        selectContact(contact);
        waiting();
        removeSelectedContactFromGroup();
    }

    private void removeSelectedContactFromGroup() {
        click(By.name("remove"));
    }

    private void selectGroupWithContactList(GroupData group) {
        new Select(manager.driver.findElement(By.xpath("//select[@name='group']"))).selectByValue(group.id());
    }

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }


    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        attach(By.name("photo"), contact.photo());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());




    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitItemModification();
        returnToHomePage();
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContact();
    }

    public void removeContact(ContactData contact) {
        waiting();
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void openContactCreationPage() {
        click(By.linkText("add new"));
    }

    public boolean isContactsPresent() {
        return !manager.isElementPresent(By.name("selected[]"));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
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
        for (var entry : td) {
            var className = entry.findElement(By.cssSelector("td.center"));
            var checkbox = className.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var lastName = className.findElement
                    (By.xpath(String.format("//input[@id='%s']/parent::td/following-sibling::td[1]", id)))
                    .getAttribute("innerText");
                            //tr[@name='entry']/child::td[2]")).
            var firstName = className.findElement
                            (By.xpath(String.format("//input[@id='%s']/parent::td/following-sibling::td[2]", id)))
                    .getAttribute("innerText");
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }


    public String getPhones(ContactData contact) {
       return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }
    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }
    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public void openEditContactPage(ContactData contact){
        click(By.xpath(
                String.format("//input[@id='%s']/../../td[8]//img", contact.id())));
    }

        public Map<String,String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        } return result;


    }
    public Map<String,String> getContactInfoFromMainPage(ContactData contact) {

        var result = new HashMap<String, String>();

        var address = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).
                getText();
        var emails = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).
                getText().replace("\n", " ");
        var phones = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).
                getText().replace("\n", " ");
            result.put("address", address);
            result.put("emails", emails);
            result.put("phones", phones);
        return result;
    }
    public Map<String,String> getContactInfoFromEditPage(ContactData contact){
        openEditContactPage(contact);

        Map<String, String> result = new HashMap<>();
        addNonEmptyValue(result, "address", "address");
        addNonEmptyValue(result, "emails","email", "email2", "email3");
        addNonEmptyValue(result, "phones","home", "mobile", "work");

        return result;
    }

    private void addNonEmptyValue(Map<String, String> result, String key, String... fields) {
        String value = Stream.of(fields)
                .map(field -> manager.driver.findElement(By.name(field)).getAttribute("value"))
                .filter(fieldValue -> !fieldValue.isEmpty())
                .collect(Collectors.joining(" "));

        if (!value.isEmpty()) {
            result.put(key, value);
        } else {
            result.put(key, "");
        }
    }

}

