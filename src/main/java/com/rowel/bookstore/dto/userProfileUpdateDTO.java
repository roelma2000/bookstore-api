package com.rowel.bookstore.dto;

import com.rowel.bookstore.model.User;
import lombok.Data;

@Data
public class userProfileUpdateDTO {
    private String name;
    private User.Address address;
    private String phoneNumber;
}
