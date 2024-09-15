package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.Follows;
import com.example.hmg.wishlistBE.repository.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides business logic for handling follow-related operations
 */
@Service
public class FollowsServiceImpl implements FollowsService {
    @Autowired
    private FollowsRepository followsRepository;

    @Override
    public Follows saveFollows(Follows follows) {
        // Saves and returns the follow entity.
        return followsRepository.save(follows);
    }

    /**
     * Fetch everyone a user follows
     */
    @Override
    public List<Follows> fetchFollowsList(String personUsername) {
        System.out.println("Welcome to fetch user's followings");
        // Retrieves and returns a list of everyone the user follows
        List<Follows> allFollows = followsRepository.findAll();
        System.out.println("Grabbed all followsRows");
        System.out.println(allFollows);
        List<Follows> userFollows = allFollows.stream()
                .filter(followsRow -> followsRow.getPersonUsername().equals(personUsername)
                )
                .toList();
        System.out.println("Filtered to users the user follows");
        System.out.println(userFollows);
        return userFollows;
    }
}