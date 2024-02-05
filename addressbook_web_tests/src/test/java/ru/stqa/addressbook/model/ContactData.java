package ru.stqa.addressbook.model;

public record ContactData(
        String id,
        String lastName,
        String firstName,
        String address,
        String email,
        String email2,
        String email3,
        String photo,
        String home,
        String mobile,
        String work,
        String fax) {
    public ContactData() {
        this("","","","","", "", "", "", "", "", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.fax);
    }
    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, email, this.email2, this.email3,this.photo, this.home, this.mobile, this.work, this.fax);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, email2, this.email3, this.photo, this.home, this.mobile, this.work, this.fax);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.email2, email3, this.photo, this.home, this.mobile, this.work, this.fax);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.email2, this.email3, photo, this.home, this.mobile, this.work, this.fax);
    }
    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.email2, this.email3, this.photo, home, this.mobile, this.work, this.fax);
    }
    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.email2, this.email3, this.photo, this.home, mobile, this.work, this.fax);
    }
    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, work, this.fax);
    }
    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.email2, this.email3, this.photo, this.home, this.mobile, this.work, fax);
    }

}
