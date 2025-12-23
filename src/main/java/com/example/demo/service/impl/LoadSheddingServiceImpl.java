package com.example.demo.service.impl;

import java.time.LocalDateTime;
import com.example.demo.entity.LoadSheddingEvent;

public class LoadSheddingServiceImpl {

    public LoadSheddingEvent createEvent() {
        LoadSheddingEvent event = new LoadSheddingEvent();
        event.setEventStart(LocalDateTime.now());
        return event;
    }
}
