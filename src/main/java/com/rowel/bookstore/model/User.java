package com.rowel.bookstore.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    private Address address;

    @Pattern(regexp = "[0-9]+", message = "Phone number should contain only digits")
    @Size(min = 10, max = 15, message = "Phone number should be between 10 and 15 digits")
    private String phoneNumber;

    private List<String> orderHistory; // Array of Order IDs

    public static class Address {
        @NotBlank(message = "Street is required")
        private String street;

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "Zip is required")
        private String zip;

        @NotBlank(message = "Country is required")
        private String country;
    }
}
