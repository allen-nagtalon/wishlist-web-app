package com.allennagtalon.wishlistwebapp.dto;

import java.util.List;

import com.allennagtalon.wishlistwebapp.model.CustomUser;
import com.allennagtalon.wishlistwebapp.model.Wish;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResponse {
  
  private int statusCode;
  private String error;
  private String message;
  private String token;
  private String refreshToken;
  private String expirationTime;
  private String firstName;
  private String lastName;
  private String username;
  private String role;
  private String password;
  private List<Wish> wishes;
  private CustomUser customUser;

}
