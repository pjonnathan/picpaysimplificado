package com.example.picpaysimplificado.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserDocument(String document);
    Optional<User> findUserDocument(Long id);
}
