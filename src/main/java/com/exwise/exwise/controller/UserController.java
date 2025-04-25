package com.exwise.exwise.controller;

import com.exwise.exwise.dto.UserDTO;
import com.exwise.exwise.dto.response.ApiResponse;
import com.exwise.exwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> create(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> update(@PathVariable long id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> delete(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
