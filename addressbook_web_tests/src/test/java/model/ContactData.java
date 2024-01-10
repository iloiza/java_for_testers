package model;

public record ContactData(String id,String lastName, String firstName, String address, String email, String phones) {
    public ContactData() {
        this("","","","","","");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.email, this.phones);
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.email, this.phones);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.email, this.phones);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.phones);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, email, this.phones);
    }

    public ContactData withPhones(String phones) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, phones);
    }

}
