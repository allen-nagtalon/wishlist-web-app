package com.allennagtalon.wishlistwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allennagtalon.wishlistwebapp.model.Wishlist;
import com.allennagtalon.wishlistwebapp.repository.WishlistRepository;

@Controller
@RequestMapping(path="/wishlists")
public class WishlistController {
  @Autowired
  private WishlistRepository wishlistRepository;

  @GetMapping(path="/user/{userId}")
  public @ResponseBody Iterable<Wishlist> getWishlistsByUserId(@PathVariable Integer userId) {
    return wishlistRepository.findByAuthorId(userId);
  }
}
