package com.example.demo.service.impl;

import com.example.demo.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Override
    public String getUserRole(String username) {
        return "USER";
    }
}
