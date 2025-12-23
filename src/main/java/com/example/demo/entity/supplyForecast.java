package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SupplyForecast {

    @Id
    @GeneratedValue
    private Long id;

    private double availableMW;

    public double getAvailableMW() {
        return availableMW;
    }
}
