package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.entity.Wish;

import java.security.Principal;
import java.util.List;

/**
 * Defines methods for CRUD operations and additional business logic.
 */
public interface WishService {
    /**
     * Saves a wish
     */
    Wish saveWish(Wish Wish);

    /**
     * Updates an existing Wish entity.
     * @param Wish the Wish with updated information
     * @param wishId the ID of the Wish to update
     * @return the updated Wish
     */
    Wish updateWish(Wish Wish, Long wishId);

    /**
     * Deletes a Wish entity by its ID.
     * @param wishId the ID of the Wish to delete
     */
    void deleteWishById(Long wishId);

    /**
     * Increases the number of a wish that has been bought by 1
     */
    Wish incrementWishBoughtNumber(Long wishId);

    /**
     * Fetches the list of all Wish entities.
     */
    List<Wish> fetchWishList();

    /**
     * Fetches the list of Wishes made by user
     * If the current user is the user who made the wish, wishNumberBought is redacted
     */
    List<Wish> fetchWishList(User user, Principal principal);

}