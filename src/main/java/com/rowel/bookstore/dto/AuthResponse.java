package com.rowel.bookstore.dto;

import com.rowel.bookstore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String id;
    private String token;
    private String email;

    public AuthResponse(String id, String email, String token) {
        this.id = id;
        this.token = token;
        this.email = email;
    }

}