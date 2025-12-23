package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    private String zoneName;
    private int priorityLevel;
    private long population;
    private boolean active;

    public Long getId() {
        return id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public long getPopulation() {
        return population;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
