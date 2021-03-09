package com.tkato.myKanBan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Ticket {
    @Id
    @Column (name="ticketid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @Column
    @NotEmpty(message = "Please provide a ticket title")
    private String title;

    @Column
    private String description;

    @Column (name="projectid")
    @NotEmpty(message = "Please provide the ID for the associated project")
    private int projectId;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public int getId() {
        return ticketId;
    }

    public void setId(int id) {
        this.ticketId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        for (Status stat : Status.values()) {
            Status temp = Status.valueOf(status);
            if (stat.equals(temp)) {
                this.status = temp;
            }
        }
    }

    public enum Status {
        TODO,
        INPROGRESS,
        DONE,
        CANCELLED
    }
}
