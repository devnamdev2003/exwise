package com.exwise.exwise.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.exwise.exwise.dto.UserDTO;
import com.exwise.exwise.dto.response.ApiResponse;
import com.exwise.exwise.entity.User;
import com.exwise.exwise.service.UserService;
import com.exwise.exwise.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBackupFrequency(user.getBackupFrequency());
        dto.setIsBackup(user.getIsBackup());
        dto.setLastBackup(user.getLastBackup());
        dto.setNotifications(user.getNotifications());
        dto.setThemeMode(user.getThemeMode());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    private User mapToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .backupFrequency(dto.getBackupFrequency())
                .isBackup(dto.getIsBackup())
                .lastBackup(dto.getLastBackup())
                .notifications(dto.getNotifications())
                .themeMode(dto.getThemeMode())
                .isActive(dto.isActive())
                .build();
    }

    @Override
    public ApiResponse<UserDTO> createUser(UserDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            return new ApiResponse<>("error", 400, "Email must not be empty", null, true);
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            return new ApiResponse<>("error", 400, "Password must not be empty", null, true);
        }

        User user = mapToEntity(dto);
        User saved = userRepository.save(user);
        return new ApiResponse<>("success", 201, "User created successfully", mapToDTO(saved), false);
    }

    @Override
    public ApiResponse<UserDTO> getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse<>("error", 404, "User not found", null, true);
        }
        return new ApiResponse<>("success", 200, "User fetched successfully", mapToDTO(optional.get()), false);
    }

    @Override
    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> list = userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ApiResponse<>("success", 200, "All users fetched", list, false);
    }

    @Override
    public ApiResponse<UserDTO> updateUser(Long id, UserDTO dto) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse<>("error", 404, "User not found", null, true);
        }

        User user = optional.get();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBackupFrequency(dto.getBackupFrequency());
        user.setIsBackup(dto.getIsBackup());
        user.setLastBackup(dto.getLastBackup());
        user.setNotifications(dto.getNotifications());
        user.setThemeMode(dto.getThemeMode());
        user.setActive(dto.isActive());

        User updated = userRepository.save(user);
        return new ApiResponse<>("success", 200, "User updated successfully", mapToDTO(updated), false);
    }

    @Override
    public ApiResponse<UserDTO> deleteUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse<>("error", 404, "User not found", null, true);
        }
        userRepository.delete(optional.get());
        return new ApiResponse<>("success", 200, "User deleted successfully", null, false);
    }

}
