package com.example.demo.service.impl;

import com.example.demo.service.LoadSheddingService;
import org.springframework.stereotype.Service;

@Service
public class LoadSheddingServiceImpl implements LoadSheddingService {

    @Override
    public String applyLoadShedding(Long zoneId) {
        return "Load shedding applied for zone " + zoneId;
    }
}
