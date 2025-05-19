package com.yourorg.talkSafe.backend.service;

import com.yourorg.talkSafe.backend.auth.SignupRequest;
import com.yourorg.talkSafe.backend.auth.User;
import com.yourorg.talkSafe.backend.auth.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public User register(SignupRequest req) {
        // Check if email already exists
        userRepo.findByEmail(req.name())
                .ifPresent(u -> { throw new RuntimeException("Email already registered"); });
        User u = new User();
        u.setEmail(req.email());
        u.setName(req.name());
        u.setPasswordHash(encoder.encode(req.password()));
        try {
            return userRepo.save(u);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // Handle unique constraint violations
            throw new RuntimeException("Email already registered");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        User u = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        System.out.println(u);
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPasswordHash(), List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}