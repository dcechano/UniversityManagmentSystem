package com.example.ums.entities.person;

import com.example.ums.entities.AbstractEntity;
import com.example.ums.entities.Role;

import javax.persistence.*;
import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "PERSON")
public class Person extends AbstractEntity {

    @Column(name = "USERNAME")
    protected String username;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Column(name = "PASSWORD")
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PERSON_ROLES",
        joinColumns = @JoinColumn(name = "ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE")
    )
    @Enumerated(EnumType.STRING)
    protected List<Role> roles;

    protected Person() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                "} " + super.toString();
    }
}
