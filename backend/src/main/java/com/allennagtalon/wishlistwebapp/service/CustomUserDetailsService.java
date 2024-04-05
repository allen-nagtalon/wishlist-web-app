package com.allennagtalon.wishlistwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.allennagtalon.wishlistwebapp.repository.CustomUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private CustomUserRepository customUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return customUserRepository.findByUsername(username).orElseThrow();
  }
  
}
