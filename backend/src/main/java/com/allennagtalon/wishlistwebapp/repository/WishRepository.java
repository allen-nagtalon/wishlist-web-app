package com.allennagtalon.wishlistwebapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.allennagtalon.wishlistwebapp.model.Wish;

public interface WishRepository extends CrudRepository<Wish, Integer> {
  
  Iterable<Wish> findWishesByWishlistsId(final Integer wishlistId);

}