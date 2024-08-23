package com.aerarium.aerarium.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUserEmail(String userEmail);
}
