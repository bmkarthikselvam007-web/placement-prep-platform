package com.placementprep.backend.service;

import com.placementprep.backend.entity.User;
import com.placementprep.backend.repository.UserRepository;
import com.placementprep.backend.util.JwtUtil;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {

    user.setPassword(
            passwordEncoder.encode(user.getPassword())
    );

    return userRepository.save(user);
}

    public String loginUser(User loginRequest) {

    User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(
            loginRequest.getPassword(),
            user.getPassword()
    )) {
        return JwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    return "Invalid password";
}
    private final BCryptPasswordEncoder passwordEncoder =
        new BCryptPasswordEncoder();
}