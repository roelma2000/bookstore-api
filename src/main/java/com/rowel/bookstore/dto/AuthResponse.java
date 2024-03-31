package com.rowel.bookstore.dto;

import com.rowel.bookstore.model.User;

public class AuthResponse {
    private User user;
    private String token;

    public AuthResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

}