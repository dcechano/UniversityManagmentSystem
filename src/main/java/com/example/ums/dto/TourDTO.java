package com.example.ums.dto;

import com.example.ums.entities.Tour;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TourDTO {
    private String firstName;

    private String lastName;

    private String scheduledFor;

    public TourDTO() {
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

    public String getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(String scheduledFor) {
        this.scheduledFor = scheduledFor;
    }

    public Tour getTour() {
        LocalDateTime dateTime = LocalDateTime.parse(scheduledFor, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new Tour(firstName, lastName, dateTime);
    }
}
