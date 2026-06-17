package com.placementprep.backend.controller;

import com.placementprep.backend.dto.LoginResponse;
import com.placementprep.backend.entity.User;
import com.placementprep.backend.service.UserService;
import com.placementprep.backend.util.JwtUtil;
import com.placementprep.backend.dto.ProfileResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
public LoginResponse loginUser(@RequestBody User user) {
    String token = userService.loginUser(user);
    return new LoginResponse(token);
}
@GetMapping("/validate")
public String validateToken(@RequestParam String token) {

    return JwtUtil.extractEmail(token);
}
@GetMapping("/profile")
public ProfileResponse getProfile(@RequestHeader(value = "Authorization", required = false) String authHeader) {

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return new ProfileResponse(null, "Missing or invalid Authorization header");
    }

    try {
        String token = authHeader.replace("Bearer ", "");
        String email = JwtUtil.extractEmail(token);

        return new ProfileResponse(email, "Profile accessed successfully");
    } catch (Exception e) {
        return new ProfileResponse(null, "Invalid or expired token");
    }
}
}
