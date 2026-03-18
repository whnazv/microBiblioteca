package com.whnazv.events.v1;

import java.time.Instant;

public class UserRegisteredEventV1 {
    private UserClient user;
    private Instant createdAt;

    public UserRegisteredEventV1() {}

    public UserRegisteredEventV1(UserClient user, Instant createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }

    
    public UserClient getUser() { return user; }
    public void setUser(UserClient user) { this.user = user; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
