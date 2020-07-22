package com.example.ums.entities.person;

import com.example.ums.entities.AbstractEntity;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.util.Objects;

@MappedSuperclass
public abstract class Person extends AbstractEntity {

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    protected Person() {
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

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                "} " + super.toString();
    }
}
