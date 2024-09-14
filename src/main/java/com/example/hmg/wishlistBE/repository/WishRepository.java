package com.example.hmg.wishlistBE.repository;

import com.example.hmg.wishlistBE.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
}
