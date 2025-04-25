package com.exwise.exwise.repository;

import com.exwise.exwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
