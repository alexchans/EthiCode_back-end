package com.example.ethicodebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Endpoint to verify identity
    @PostMapping("/login")
    public ResponseEntity<Login> login(@RequestBody Login loginCredentials) {
        Login login = loginService.findLoginByCredentials(loginCredentials);
        if (login != null) {
            return ResponseEntity.ok(login);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to create or update the login info (both username and password)
    @PostMapping("/signin")
    public ResponseEntity<Login> saveOrUpdateLogin(@RequestBody Login login) {
        Login savedOrUpdatedLogin = loginService.saveLogin(login);
        return ResponseEntity.ok(savedOrUpdatedLogin);
    }

    // Endpoint to update the password
    // Assumes that the request body will contain a Login object with the username and new password
    @PutMapping("/update")
    public ResponseEntity<Login> updatePassword(@RequestBody Login loginDetails) {
        Login updatedLogin = loginService.updatePassword(loginDetails.getUsername(), loginDetails.getPassword());
        if (updatedLogin != null) {
            return ResponseEntity.ok(updatedLogin);
        }
        return ResponseEntity.notFound().build();
    }
}
