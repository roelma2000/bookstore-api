package com.rowel.bookstore.service;
import com.rowel.bookstore.dto.AuthResponse;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
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
            // Return both user information and token in a response object
            return new AuthResponse(user, token);
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

    @Override
    public User updateProfile(String id, User user) {
        // Similar to updateUser, but might include specific fields related to the profile
        return updateUser(id, user);
    }



}
