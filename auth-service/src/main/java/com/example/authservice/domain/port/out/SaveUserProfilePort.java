package com.example.authservice.domain.port.out;

import com.example.authservice.domain.model.UserRegistration;

public interface SaveUserProfilePort {
    void saveProfile(UserRegistration registration);
}
