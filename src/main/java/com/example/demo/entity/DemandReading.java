package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DemandReading {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Zone zone;

    private double demandMW;
    private LocalDateTime recordedAt;

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public double getDemandMW() {
        return demandMW;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
}
