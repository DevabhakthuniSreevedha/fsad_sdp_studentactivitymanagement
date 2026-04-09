package com.klef.fsad.sdp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.dto.AuthRequestDTO;
import com.klef.fsad.sdp.security.JwtUtil;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController
{
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request)
    {
        try
        {
            System.out.println("Login Request Received");
            System.out.println("Login: " + request.getLogin());
            System.out.println("Role: " + request.getRole());

            UserDetails userDetails = userService.loadUserByUsername(request.getLogin());

            String actualRole = userDetails.getAuthorities()
                    .iterator().next().getAuthority();

            System.out.println("Actual Role: " + actualRole);

            if (!actualRole.equalsIgnoreCase(request.getRole()))
            {
                return ResponseEntity.status(403).body("Invalid Role");
            }

            boolean isValid = false;

            if (actualRole.equalsIgnoreCase("ADMIN"))
            {
                isValid = request.getPassword().equals(userDetails.getPassword());
            }
            else if (actualRole.equalsIgnoreCase("FACULTY") || actualRole.equalsIgnoreCase("STUDENT"))
            {
                isValid = passwordEncoder.matches(request.getPassword(), userDetails.getPassword());
            }

            if (!isValid)
            {
                return ResponseEntity.status(401).body("Login Invalid");
            }

            String token = jwtUtil.generateToken(userDetails);
            Object userObj = userService.getUserByLogin(request.getLogin());

            if (userObj == null)
            {
                return ResponseEntity.status(500).body("User object not found");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", actualRole);
            response.put("user", userObj);

            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
}