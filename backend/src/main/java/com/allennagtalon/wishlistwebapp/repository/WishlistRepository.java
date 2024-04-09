package com.allennagtalon.wishlistwebapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.allennagtalon.wishlistwebapp.model.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Integer>{

  Iterable<Wishlist> findByAuthorId(final Integer authorId);

}
