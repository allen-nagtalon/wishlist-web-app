package com.allennagtalon.wishlistwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allennagtalon.wishlistwebapp.model.Wish;
import com.allennagtalon.wishlistwebapp.repository.WishRepository;

@Controller
@RequestMapping(path="/wish")
public class WishController {
  @Autowired
  private WishRepository wishRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewWish (
    @RequestParam String title,
    @RequestParam String description,
    @RequestParam String url
    ) {
      Wish wish = new Wish();
      wish.setTitle(title);
      wish.setDescription(description);
      wish.setUrl(url);
      wishRepository.save(wish);
      return "Saved";
    }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Wish> getAllWishes() {
    return wishRepository.findAll();
  }

  @GetMapping(path="/{wishlistId}")
  public @ResponseBody Iterable<Wish> getAllWishesByWishlistId(@PathVariable Integer wishlistId) {
    return wishRepository.findWishesByWishlistsId(wishlistId);
  }
}