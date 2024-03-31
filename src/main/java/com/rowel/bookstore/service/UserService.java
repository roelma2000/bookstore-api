package com.rowel.bookstore.service;

import com.rowel.bookstore.dto.AuthResponse;
import com.rowel.bookstore.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(String id);
    User createUser(User user);
    User updateUser(String id, User user);
    void deleteUser(String id);
    User registerUser(User user);
   AuthResponse loginUser(String email, String password);
    void logoutUser(String token);
    Optional<User> getProfile(String id);
    User updateProfile(String id, User user);


}
