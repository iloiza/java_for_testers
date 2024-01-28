package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstName;
    public String lastName;
    public String address;
    @ManyToMany(mappedBy = "contacts")

    public List<GroupRecord> group;
    public ContactRecord (int id, String firstName, String lastName, String address){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public ContactRecord(){

    }

}
