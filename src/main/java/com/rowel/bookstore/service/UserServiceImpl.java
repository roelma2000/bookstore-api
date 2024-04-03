package com.rowel.bookstore.service;
import com.rowel.bookstore.dto.AuthResponse;
import com.rowel.bookstore.dto.userAccessDTO;
import com.rowel.bookstore.dto.userProfileUpdateDTO;
import com.rowel.bookstore.exceptions.CustomConflictException;
import com.rowel.bookstore.exceptions.CustomNotFoundException;
import com.rowel.bookstore.model.User;
import com.rowel.bookstore.repository.UserRepository;
import com.rowel.bookstore.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email already in use.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // updateUser will not update email and password
    @Override
    public User updateUser(String id, userProfileUpdateDTO updatedUser) {
        return userRepository.findById(id).map(user -> {
            if(updatedUser.getName() != null) {
                user.setName(updatedUser.getName());
            }
            if(updatedUser.getAddress() != null){
                user.setAddress(updatedUser.getAddress());
            }

            if(updatedUser.getPhoneNumber() != null){
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AuthResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtTokenProvider.generateToken(user);
            // Return both id, email, and token in a response object
            return new AuthResponse(user.getId(), user.getEmail(), token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public void logoutUser(String token) {
        // JWT is stateless, so logging out might involve client-side token deletion.
        logger.info("User logged out. Token: {}", token);
    }

    @Override
    public Optional<User> getProfile(String id) {
        return userRepository.findById(id);
    }

    // updateProfile will not patch email and password
    @Override
    public User updateProfile(String id, User userProfile) {
        // Similar to updateUser, but might include specific fields related to the profile
        return userRepository.findById(id).map(user -> {
            user.setName(userProfile.getName());
            //user.setEmail(userProfile.getEmail());
            //user.setPassword(passwordEncoder.encode(userProfile.getPassword()));
            user.setAddress(userProfile.getAddress());
            user.setPhoneNumber(userProfile.getPhoneNumber());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));

    }

    @Override
    public String updateAuth(String id, userAccessDTO userAuth) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User ID not found"));

        String email = userAuth.getEmail();
        String pass = userAuth.getPassword();

        if(email != null && !email.trim().isEmpty()) {
            boolean emailExists = userRepository.findByEmail(email)
                    .map(user -> !user.getId().equals(id))
                    .orElse(false);

            if(emailExists) {
                throw new CustomConflictException("Email already in use by another account.");
            }
            existingUser.setEmail(email.trim());
        }

        if(pass != null && !pass.trim().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(pass.trim()));
        }

        userRepository.save(existingUser);
        return email;
    }


}
