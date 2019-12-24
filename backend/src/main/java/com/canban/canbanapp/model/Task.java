package com.canban.canbanapp.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tbl_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 250)
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "process", nullable = false)
    private String process;

    @Column(name = "dueDate", nullable = false)
    @Type(type = "date")
    private Date dueDate;

    @Column(name = "completedDate", nullable = true)
    @Type(type = "date")
    private Date completedDate;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
