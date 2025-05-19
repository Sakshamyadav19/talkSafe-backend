package com.yourorg.talkSafe.backend.auth;

import jakarta.persistence.*;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;  // not unique

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}