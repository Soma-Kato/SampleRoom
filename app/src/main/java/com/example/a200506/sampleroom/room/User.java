package com.example.a200506.sampleroom.room;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    private int status;

    private String description;

    public User(long id, String name, int status, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
