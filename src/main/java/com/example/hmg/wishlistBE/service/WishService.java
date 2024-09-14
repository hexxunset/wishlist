package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.Wish;
import java.util.List;

/**
 * Service interface for Wish entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface WishService {
    /**
     * Saves a Wish entity.
     * @param Wish the Wish to save
     * @return the saved Wish
     */
    Wish saveWish(Wish Wish);

    /**
     * Fetches the list of all Wish entities.
     * @return a list of wish
     */
    List<Wish> fetchWishList();

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

}