package com.example.demo.controller;

import com.example.demo.entity.SupplyForecast;
import com.example.demo.repository.SupplyForecastRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply-forecast")
public class SupplyForecastController {

    private final SupplyForecastRepository repository;

    public SupplyForecastController(SupplyForecastRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public SupplyForecast create(@RequestBody SupplyForecast forecast) {
        return repository.save(forecast);
    }

    @GetMapping
    public List<SupplyForecast> getAll() {
        return repository.findAll();
    }
}
