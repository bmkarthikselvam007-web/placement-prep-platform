package com.placementprep.backend.controller;

import com.placementprep.backend.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @GetMapping("/student/dashboard")
    public String studentDashboard(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String role = JwtUtil.extractRole(token);

        if (role.equals("STUDENT")) {
            return "Welcome Student Dashboard";
        }

        return "Access denied: Students only";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String role = JwtUtil.extractRole(token);

        if (role.equals("ADMIN")) {
            return "Welcome Admin Dashboard";
        }

        return "Access denied: Admins only";
    }
}