package com.example.hmg.wishlistBE.repository;

import com.example.hmg.wishlistBE.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowsRepository extends JpaRepository<Follows, String> {
}
