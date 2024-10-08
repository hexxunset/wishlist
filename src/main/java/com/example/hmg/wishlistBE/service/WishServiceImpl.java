package com.example.hmg.wishlistBE.service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.entity.Wish;
import com.example.hmg.wishlistBE.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of WishService.
 * Provides business logic for handling wish-related operations.
 */
@Service // Marks this class as a Spring service component.
public class WishServiceImpl implements WishService {

    @Autowired
    private WishRepository wishRepository; // Injects the WishRepository dependency.

    @Override
    public Wish saveWish(Wish wish) {
        // Saves and returns the wish entity.
        return wishRepository.save(wish);
    }

    @Override
    public Wish updateWish(Wish wish, Long wishId) {
        // Finds the existing wish by ID.
        Wish wishDB = wishRepository.findById(wishId).get();

        // Updates fields if they are not null or empty.
        if (Objects.nonNull(wish.getPersonId())) {
            wishDB.setPersonId(wish.getPersonId());
        }
        if (Objects.nonNull(wish.getWishName()) && !"".equalsIgnoreCase(wish.getWishName())) {
            wishDB.setWishName(wish.getWishName());
        }
        if (Objects.nonNull(wish.getWishDescription()) && !"".equalsIgnoreCase(wish.getWishDescription())) {
            wishDB.setWishDescription(wish.getWishDescription());
        }
        // WishNumberBought can't be null, but let's keep the check in case that changes
        if (Objects.nonNull(wish.getWishNumberBought())) {
            wishDB.setWishNumberBought(wish.getWishNumberBought());
        }

        // Saves and returns the updated wish entity.
        return wishRepository.save(wishDB);
    }

    @Override
    public void deleteWishById(Long wishId) {
        // Deletes the wish entity by its ID.
        wishRepository.deleteById(wishId);
    }

    /**
     * Increases the number of a wish that has been bought by 1
     */
    @Override
    public Wish incrementWishBoughtNumber(Long wishId) {
        System.out.println("incrementing wish with id " + wishId);
        Wish wishDB = wishRepository.findById(wishId).get();
        wishDB.setWishNumberBought(wishDB.getWishNumberBought()+1);
        return wishRepository.save(wishDB);
    }

    /**
     * Fetches the list of all Wish entities.
     */
    @Override
    public List<Wish> fetchWishList() {
        // Retrieves and returns a list of all department entities.
        return (List<Wish>) wishRepository.findAll();
    }

    /**
     * Fetches the list of Wishes made by user
     * If the current user is the user who made the wish, wishNumberBought is redacted
     */
    @Override
    public List<Wish> fetchWishList(User user, Principal principal) {
        System.out.println("Welcome to fetch user's wishlist");
        // Retrieves and returns a list of all wish entities.
        List<Wish> allWishes = wishRepository.findAll();
        List<Wish> userWishes = allWishes.stream()
//                .map(Wish::getPersonId)
                .filter(l -> l.getPersonId().equals(user.getId())
                ) // here
                .toList();
        System.out.println(userWishes);
        // Remove information about if the wish has been bought if it's the user's own wish (no spoilers!)
        if (user.getUsername().equals(principal.getName())) {
            userWishes.forEach(wish -> wish.setWishNumberBought(null));
        }
        return userWishes;
    }
}