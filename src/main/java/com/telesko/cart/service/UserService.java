package com.telesko.cart.service;

import com.telesko.cart.model.Users;
import com.telesko.cart.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
