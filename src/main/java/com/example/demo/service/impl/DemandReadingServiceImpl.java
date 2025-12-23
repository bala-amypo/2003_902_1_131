package com.example.demo.service.impl;

import java.time.LocalDateTime;
import com.example.demo.entity.DemandReading;

public class DemandReadingServiceImpl {

    public void save(DemandReading r) {
        r.setRecordedAt(LocalDateTime.now());
    }
}
