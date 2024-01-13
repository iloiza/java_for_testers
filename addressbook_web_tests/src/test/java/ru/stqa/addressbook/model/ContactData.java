package ru.stqa.addressbook.model;

public record ContactData(String id,String lastName, String firstName, String address, String email, String phones, String photo) {
    public ContactData() {
        this("","","","","","", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.email, this.phones, this.photo);
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.email, this.phones, this.photo);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.email, this.phones, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.phones, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, email, this.phones, this.photo);
    }

    public ContactData withPhones(String phones) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, phones, this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.phones, photo);
    }

}
