package com.example.user_service.repository;

import com.example.user_service.entity.User;
import com.example.user_service.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByIdUserAndIdRole(Long userId, Long roleId);
}
