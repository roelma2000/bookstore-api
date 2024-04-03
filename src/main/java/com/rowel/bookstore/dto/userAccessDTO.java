package com.rowel.bookstore.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class userAccessDTO {
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    @AssertTrue(message = "Either email or password must be provided")
    public boolean isValid() {
        return (email != null && !email.trim().isEmpty()) ||
                (password != null && !password.trim().isEmpty());
    }
}
