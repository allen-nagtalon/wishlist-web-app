package com.allennagtalon.wishlistwebapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allennagtalon.wishlistwebapp.model.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {
  
  Optional<CustomUser> findByUsername(String username);
  
}
