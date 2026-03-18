package com.example.authservice.domain.port.out;

import com.example.authservice.domain.model.User;

public interface UserEventPublisherPort {

    void publishUserRegistered(User user);

}