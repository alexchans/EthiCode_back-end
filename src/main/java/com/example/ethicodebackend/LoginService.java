package com.example.ethicodebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login findLoginByCredentials(Login login) {
        // Use the username to find the Login object from the database
        Login foundLogin = loginRepository.findById(login.getUsername()).orElse(null);

        // Check if a user was found and if the passwords match
        if (foundLogin != null && foundLogin.getPassword().equals(login.getPassword())) {
            return foundLogin;  // Return the found login if credentials are correct
        }

        return null;  // Return null if no user was found or if the password does not match
    }


    public Login saveLogin(Login login) {
        return loginRepository.save(login);
    }

    public Login updatePassword(String username, String newPassword) {
        Login login = loginRepository.findById(username).orElse(null);
        if (login != null) {
            login.setPassword(newPassword);
            loginRepository.save(login);
        }
        return login;
    }
}
