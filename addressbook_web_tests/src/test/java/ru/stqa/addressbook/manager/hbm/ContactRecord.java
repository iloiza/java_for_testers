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
    public String email;
    public String mobile;
    public String middlename = new String();
    public String nickname= new String();
    public String company= new String();
    public String title= new String();
    public String home= new String();
    public String work= new String();
    public String fax= new String();
    public String email2= new String();
    public String email3= new String();
    public String homepage= new String();
    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))

    public List<GroupRecord> group;
    public ContactRecord (int id, String firstName, String lastName, String address, String email, String mobile){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public ContactRecord(){

    }

}
