package com.allennagtalon.wishlistwebapp.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.allennagtalon.wishlistwebapp.dto.RequestResponse;
import com.allennagtalon.wishlistwebapp.model.CustomUser;
import com.allennagtalon.wishlistwebapp.model.Role;
import com.allennagtalon.wishlistwebapp.repository.CustomUserRepository;

@Service
public class AuthService {
  
  @Autowired
  private CustomUserRepository customUserRepository;

  @Autowired
  private JWTUtils jwtUtils;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  public RequestResponse signUp(RequestResponse request) {
    RequestResponse response = new RequestResponse();
    try {
      var user = CustomUser.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

      CustomUser result = customUserRepository.save(user);

      if (result != null && result.getId() > 0) {
        response.setCustomUser(result);
        response.setMessage("User Saved Successfully");
        response.setStatusCode(200);
      }
    } catch (Exception e) {
      response.setError(e.getMessage());
      response.setStatusCode(500);
    }

    return response;
  }

  public RequestResponse signIn(RequestResponse request) {
    RequestResponse response = new RequestResponse();

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
      
      var user = customUserRepository.findByUsername(request.getUsername()).orElseThrow();
      System.out.println("USER IS: " + user);
      var jwt = jwtUtils.generateToken(user);
      var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

      response.setStatusCode(200);
      response.setToken(jwt);
      response.setRefreshToken(refreshToken);
      response.setExpirationTime("24Hr");
      response.setMessage("Successfully Signed In");
    } catch (Exception e) {
      response.setError(e.getMessage());
      response.setStatusCode(500);
    }

    return response;
  }

  public RequestResponse refreshToken(RequestResponse request) {
    RequestResponse response = new RequestResponse();
    
    String username = jwtUtils.extractUsername(request.getToken());
    CustomUser user = customUserRepository.findByUsername(username).orElseThrow();

    if (jwtUtils.isTokenValid(request.getToken(), user)) {
      var jwt = jwtUtils.generateToken(user);
      response.setStatusCode(200);
      response.setToken(jwt);
      response.setRefreshToken(request.getToken());
      response.setExpirationTime("24Hr");
      response.setMessage("Successfully Signed In");
    }

    return response;
  }
}
