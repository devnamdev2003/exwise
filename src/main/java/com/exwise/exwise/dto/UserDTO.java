package com.exwise.exwise.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String backupFrequency;

    private Boolean isBackup;

    private LocalDateTime lastBackup;

    private Boolean notifications;

    private String themeMode;

    private boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public boolean isActive() {
        return this.isActive;
    }
}
