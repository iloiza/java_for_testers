package ru.stqa.addressbook.model;

public record ContactData(
        String id,
        String lastName,
        String firstName,
        String address,
        String email,
        String photo,
        String home,
        String mobile,
        String work,
        String fax) {
    public ContactData() {
        this("","","","","", "", "", "", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.email, this.photo, this.home, this.mobile, this.work, this.fax);
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.email, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.email, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, email, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, photo, this.home, this.mobile, this.work, this.fax);
    }
    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.photo, home, this.mobile, this.work, this.fax);
    }
    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.photo, this.home, mobile, this.work, this.fax);
    }
    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.photo, this.home, this.mobile, work, this.fax);
    }
    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.photo, this.home, this.mobile, this.work, fax);
    }

}
