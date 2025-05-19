package com.yourorg.talkSafe.backend.auth;

import com.yourorg.talkSafe.backend.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {
    private final AuthService authService;
    private final JwtTokenProvider jwtProvider;
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;

    public AuthFacade(AuthService authService,
                      JwtTokenProvider jwtProvider,
                      AuthenticationManager authManager,
                      UserRepository userRepo) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.authManager = authManager;
        this.userRepo = userRepo;
    }

    /** Register a new user */
    public AuthResponse signup(SignupRequest req) {
        var usr = authService.register(req);
        String token = jwtProvider.generateToken(req.email());
        return new AuthResponse(usr.getName(), token);
    }

    /**
     * Authenticate, look up the user’s name, and return both name and token.
     * Throws RuntimeException("User does not exist") if the email isn’t found.
     */
    public AuthResponse signin(AuthRequest req) {
        // 1) Authenticate credentials (will throw AuthenticationException if bad pw)
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        // 2) Load the user (should always succeed if step 1 passed, but guard anyway)
        User user = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        // 3) Issue the token
        String token = jwtProvider.generateToken(req.email());

        // 4) Return both the human name and the JWT
        return new AuthResponse(user.getName(), token);
    }
}
