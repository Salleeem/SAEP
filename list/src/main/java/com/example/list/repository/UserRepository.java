package com.example.list.repository;

import com.example.list.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByEmailAndSenha(String email, String senha);
    
}
