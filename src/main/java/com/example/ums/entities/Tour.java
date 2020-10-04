package com.example.ums.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TOUR")
public class Tour extends AbstractEntity{

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "SCHEDULED_FOR")
    private LocalDateTime scheduledFor;

    @PrePersist
    @PreUpdate
    public void init() {
        modifiedAt = LocalDateTime.now();
    }


    public Tour() {
        createdAt = LocalDateTime.now();
    }

    public Tour(String firstName, String lastName, LocalDateTime scheduledFor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = LocalDateTime.now();
        this.scheduledFor = scheduledFor;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(LocalDateTime scheduledFor) {
        this.scheduledFor = scheduledFor;
    }
}
