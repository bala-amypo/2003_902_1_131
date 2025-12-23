package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ZoneRestorationRecord {

    @Id
    @GeneratedValue
    private Long eventId;

    @ManyToOne
    private Zone zone;

    private LocalDateTime restoredAt;

    public Long getEventId() {
        return eventId;
    }

    public Zone getZone() {
        return zone;
    }

    public LocalDateTime getRestoredAt() {
        return restoredAt;
    }
}
