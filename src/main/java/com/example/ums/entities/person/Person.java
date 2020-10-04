package com.example.ums.entities.person;

import com.example.ums.entities.AbstractEntity;
import com.example.ums.entities.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NamedEntityGraph(name = "person-roles", attributeNodes = {@NamedAttributeNode(value = "roles")})
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
    protected Collection<Role> roles;


    public Person() {
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
