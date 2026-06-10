package com.telesko.cart.service;

import com.telesko.cart.model.Users;
import com.telesko.cart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    // We will create this Bean in the SecurityConfig next
    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // --- NEW METHOD FOR JWT LOGIN ---
    public String verify(Users user) {
        // Trigger Spring's authentication process to check the DB and BCrypt hash
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            // Generate and return the JWT string!
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Fail";
        }
    }
}
