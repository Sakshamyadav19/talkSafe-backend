package com.yourorg.talkSafe.backend.controller;

import com.yourorg.talkSafe.backend.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthFacade facade;
    private final UserRepository userRepo;

    public AuthController(AuthFacade facade, UserRepository userRepo) {
        this.facade = facade;
        this.userRepo = userRepo;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        try {
            // This now returns AuthResponse(name, token)
            AuthResponse resp = facade.signup(req);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            AuthResponse resp = facade.signin(req);
            return ResponseEntity.ok(resp);
        } catch (AuthenticationException e) {
            // invalid credentials (wrong password)
            return ResponseEntity.status(401)
                    .body("Invalid user credentials");
        } catch (RuntimeException e) {
            // user lookup failures (e.g. not found)
            return ResponseEntity.status(400)
                    .body(e.getMessage());
        }
    }


}
