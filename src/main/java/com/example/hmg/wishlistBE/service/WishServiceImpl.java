package com.example.hmg.wishlistBE.service;

import java.util.List;
import java.util.Objects;

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
    public List<Wish> fetchWishList() {
        // Retrieves and returns a list of all department entities.
        return (List<Wish>) wishRepository.findAll();
    }

    @Override
    public List<Wish> fetchWishList(Long personId) {
        System.out.println("Welcome to fetch user's wishlist");
        // Retrieves and returns a list of all wish entities.
        List<Wish> allWishes = wishRepository.findAll();
        List<Wish> userWishes = allWishes.stream()
//                .map(Wish::getPersonId)
                .filter(l -> l.getPersonId().equals(personId)
                ) // here
                .toList();
        System.out.println(userWishes);
        return userWishes;
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

        // Saves and returns the updated wish entity.
        return wishRepository.save(wishDB);
    }

    @Override
    public void deleteWishById(Long wishId) {
        // Deletes the wish entity by its ID.
        wishRepository.deleteById(wishId);
    }
}