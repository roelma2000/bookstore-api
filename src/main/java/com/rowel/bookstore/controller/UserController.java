package com.rowel.bookstore.controller;

import com.rowel.bookstore.dto.userAccessDTO;
import com.rowel.bookstore.dto.userProfileUpdateDTO;
import com.rowel.bookstore.model.User;
import com.rowel.bookstore.dto.AuthResponse;
import com.rowel.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody userProfileUpdateDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable String id, @Valid @RequestBody userProfileUpdateDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody userAccessDTO loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody String token) {
        userService.logoutUser(token);
        return ResponseEntity.ok().body(Map.of("message", "User successfully logout."));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Principal principal) {
        return userService.getProfile(principal.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(Principal principal, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateProfile(principal.getName(), user));
    }

    @PatchMapping("/auth/{id}")
    public ResponseEntity<?> updateAuth(@PathVariable String id, @Valid @RequestBody userAccessDTO user) {
        userService.updateAuth(id, user);
        return ResponseEntity.ok().body(Map.of("message", "User authentication has been updated."));
    }

}
