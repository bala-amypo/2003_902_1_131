package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SupplyForecast {

    @Id
    @GeneratedValue
    private Long id;

    private double availableMW;

    public Long getId() {
        return id;
    }

    public double getAvailableMW() {
        return availableMW;
    }

    public void setAvailableMW(double availableMW) {
        this.availableMW = availableMW;
    }
}
