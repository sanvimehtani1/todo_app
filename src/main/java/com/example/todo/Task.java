package com.example.todo;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "tasks")
public class Task {

    // id, description, status, start date, target date
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date targetDate;

    @Column(nullable = false)
    private String status = "TODO"; // Default: "TODO"



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

}
