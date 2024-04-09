package com.allennagtalon.wishlistwebapp.model;

import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wishlist {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "author_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private CustomUser author;

  private String title;

  private String description;

  @ManyToMany
  @JoinTable(
    name = "list_content",
    joinColumns = @JoinColumn(name = "wishlist_id"),
    inverseJoinColumns = @JoinColumn(name = "wish_id")
  )
  private Set<Wish> listContents;
}
