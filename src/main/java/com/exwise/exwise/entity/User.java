package com.exwise.exwise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Builder.Default
    private String backupFrequency = "daily";

    @Builder.Default
    private Boolean isBackup = false;

    private LocalDateTime lastBackup;

    @Builder.Default
    private Boolean notifications = true;

    @Builder.Default
    private String themeMode = "light";

    @Builder.Default
    private boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
