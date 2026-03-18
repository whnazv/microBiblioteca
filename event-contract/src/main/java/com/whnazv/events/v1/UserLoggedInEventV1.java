package com.whnazv.events.v1;

import java.time.Instant;

public class UserLoggedInEventV1 {
    private UserClient user;
    private Instant loginAt;

    public UserLoggedInEventV1() {}

    public UserLoggedInEventV1(UserClient user, Instant loginAt) {
        this.user = user;
        this.loginAt = loginAt;
    }

    
    public UserClient getUser() { return user; }
    public void setUser(UserClient user) { this.user = user; }

    public Instant getLoginAt() { return loginAt; }
    public void setLoginAt(Instant loginAt) { this.loginAt = loginAt; }
}
