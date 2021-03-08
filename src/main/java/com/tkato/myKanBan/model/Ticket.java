package com.tkato.myKanBan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Ticket {
    @Id
    @Column (name="ticketid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @Column
    private String title;

    @Column
    private String description;

    @Column (name="projectid")
    private int projectId;

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
}
