package com.canban.canbanapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_logging")
public class TaskLogging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;
    @Column(name = "date_time")
    private Date dateTime;

    public TaskLogging() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
