package com.pesto.task_management.controller;

import com.pesto.task_management.rto.RegisterUserRto;
import com.pesto.task_management.rto.LoginRto;
import com.pesto.task_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * AuthController is a REST controller that handles authentication related requests.
 * It exposes endpoints for user registration and login.
 * It uses AuthService to perform the actual operations.
 */
@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:4300")
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * This method handles user registration requests.
     * It receives a RegisterUserRto object as request body which contains user registration details.
     * It uses AuthService to perform the registration operation.
     * @param registerUserRto - Object containing user registration details.
     * @return ResponseEntity with HTTP status.
     */
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody RegisterUserRto registerUserRto) {
        authService.signUp(registerUserRto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * This method handles user login requests.
     * It receives a LoginRto object as request body which contains user login details.
     * It uses AuthService to perform the login operation and returns a JWT token in response.
     * @param loginRequest - Object containing user login details.
     * @return ResponseEntity with LoginRto object containing JWT token and HTTP status.
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRto loginRequest) {
        String token = authService.login(loginRequest);
        LoginRto loginRes = new LoginRto();
        loginRes.setUserName(loginRequest.getUserName());
        loginRes.setToken(token);
        return ResponseEntity.ok(loginRes);
    }
}
