package com.whnazv.events.v1;

public class UserClient {
    private String keycloakId;
    private String username;
    private String email;

    public UserClient() {}

    public UserClient(String keycloakId, String username, String email) {
        this.keycloakId = keycloakId;
        this.username = username;
        this.email = email;
    }

    
    public String getKeycloakId() { return keycloakId; }
    public void setKeycloakId(String keycloakId) { this.keycloakId = keycloakId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
