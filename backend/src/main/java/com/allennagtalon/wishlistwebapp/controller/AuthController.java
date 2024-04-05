package com.allennagtalon.wishlistwebapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.allennagtalon.wishlistwebapp.dto.RequestResponse;
import com.allennagtalon.wishlistwebapp.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
  
  @Autowired
  private AuthService authService;

  @PostMapping("/sign-up")
  public ResponseEntity<RequestResponse> signUp(@RequestBody RequestResponse request) {
    return ResponseEntity.ok(authService.signUp(request));
  }

  @PostMapping("/sign-in")
  public ResponseEntity<RequestResponse> signIn(@RequestBody RequestResponse request) {
    return ResponseEntity.ok(authService.signIn(request));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request) {
    return ResponseEntity.ok(authService.refreshToken(request));
  }
  
}
