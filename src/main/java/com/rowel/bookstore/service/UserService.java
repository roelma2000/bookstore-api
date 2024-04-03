package com.rowel.bookstore.service;

import com.rowel.bookstore.dto.AuthResponse;
import com.rowel.bookstore.dto.userAccessDTO;
import com.rowel.bookstore.dto.userProfileUpdateDTO;
import com.rowel.bookstore.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(String id);
    User createUser(User user);

    // updateUser will not update email and password
    User updateUser(String id, userProfileUpdateDTO updatedUser);

    void deleteUser(String id);
    User registerUser(User user);
   AuthResponse loginUser(String email, String password);
    void logoutUser(String token);
    Optional<User> getProfile(String id);
    User updateProfile(String id, User user); //update user details except email and pass
    String updateAuth(String id, userAccessDTO user); //will update username(email) and/or password
}
