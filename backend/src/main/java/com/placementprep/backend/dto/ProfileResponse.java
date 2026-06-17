package com.placementprep.backend.dto;

public class ProfileResponse {

    private String email;
    private String message;

    public ProfileResponse(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}