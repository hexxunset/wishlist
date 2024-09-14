package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.Follows;
import com.example.hmg.wishlistBE.repository.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FollowsServiceImpl implements FollowsService {
    @Autowired
    private FollowsRepository followsRepository;

    @Override
    public Follows saveFollows(Follows follows) {
        // Saves and returns the wish entity.
        return followsRepository.save(follows);
    }

    @Override
    public List<Follows> fetchFollowsList(String personUsername) {
        System.out.println("Welcome to fetch user's followers");
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

//    @Override
//    public List<UserDto> mapFollowsToUserDto(Follows follows) {
//        Long personId = follows.getPersonId();
//        List<Follows> userFollows = fetchFollowsList(personId);
//        List<UserWishes> userWishes;
//        for (Follows row : userFollows) {
//            User followsUser =
//            String followsUsername =
//            userWishes.add(row.getFollowsId(), row.)
//        }
////        List<UserWishes> friendsWishes = userFollows.stream()
////                .map(follows -> new UserWishes(follows.getFollowsId(), )
////                        follows.getPersonId())
//    }
}