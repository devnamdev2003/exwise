package com.exwise.exwise.service;

import com.exwise.exwise.dto.UserDTO;
import com.exwise.exwise.dto.response.ApiResponse;

import java.util.List;

public interface UserService {

    ApiResponse<UserDTO> createUser(UserDTO userDTO);

    ApiResponse<UserDTO> getUserById(Long id);

    ApiResponse<List<UserDTO>> getAllUsers();

    ApiResponse<UserDTO> updateUser(Long id, UserDTO userDTO);

    ApiResponse<UserDTO> deleteUser(Long id);
}
