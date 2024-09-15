package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.Follows;

import java.util.List;

/**
 * Defines methods for CRUD operations and additional business logic.
 */
public interface FollowsService {
    Follows saveFollows(Follows follows);

    List<Follows> fetchFollowsList(String personUsername);
}
