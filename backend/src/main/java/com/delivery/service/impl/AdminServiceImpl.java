package com.delivery.service.impl;

import com.delivery.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public boolean login(String email, String password) {
        return adminEmail.equals(email) && adminPassword.equals(password);
    }
}
