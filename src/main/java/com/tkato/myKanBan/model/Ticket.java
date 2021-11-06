package com.tkato.myKanBan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Please provide a ticket title")
    private String title;

    @Column
    private String description;

    private String ticketIdentifier;

    private String projectIdentifier;

    @ManyToOne
    @JsonIgnore
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull(message = "Must start with status of TODO")
    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date target_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTicketIdentifier() {
        return ticketIdentifier;
    }

    public void setTicketIdentifier(String ticketIdentifier) {
        this.ticketIdentifier = ticketIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public Date getTarget_date() {
        return target_date;
    }

    public void setTarget_date(Date target_date) {
        this.target_date = target_date;
    }

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.created_date = now;
        this.updated_date = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_date = new Date();
    }
}
