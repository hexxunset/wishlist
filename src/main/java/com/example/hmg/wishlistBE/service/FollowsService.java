package com.example.hmg.wishlistBE.service;

import com.example.hmg.wishlistBE.entity.Follows;

import java.util.List;

public interface FollowsService {
    Follows saveFollows(Follows follows);

    List<Follows> fetchFollowsList(String personUsername);

//    List<UserDto> mapFollowsToUserDto(Follows follows);
}
