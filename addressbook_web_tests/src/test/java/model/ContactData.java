package model;

public record ContactData(String lastName, String firstName, String address, String email, String phones) {
    public ContactData() {
        this("","","","","");
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(lastName, this.firstName, this.address, this.email, this.phones);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.lastName, firstName, this.address, this.email, this.phones);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.lastName, this.firstName, address, this.email, this.phones);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.lastName, this.firstName, this.address, email, this.phones);
    }

    public ContactData withPhones(String phones) {
        return new ContactData(this.lastName, this.firstName, this.address, this.email, phones);
    }

}
