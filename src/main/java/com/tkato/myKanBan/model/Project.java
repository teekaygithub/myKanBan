package com.tkato.myKanBan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @NotEmpty(message = "Please input a project title")
    private String title;

    @Column
    private String description;

    @ManyToMany(mappedBy = "project")
    private List<User> user = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket> ticket = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date target_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_date;
    
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_date;

    public Project() {}

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.user.add(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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