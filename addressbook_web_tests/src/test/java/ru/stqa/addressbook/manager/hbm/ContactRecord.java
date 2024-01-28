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
    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))

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
