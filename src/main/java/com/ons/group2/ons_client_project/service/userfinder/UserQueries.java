package com.ons.group2.ons_client_project.service.userfinder;

import com.ons.group2.ons_client_project.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueries implements UserFinder{

    //Repo for User
    private UserSearchRepository userSearchRepository;

    //constructor
    public UserQueries(UserSearchRepository repo){
        userSearchRepository = repo;
    }

    @Override
    public List<UserInfo> getUserInfos(Long userID){
        return userSearchRepository.getUserInformation(userID);

    }

    @Override
    public List<UserInfo> getAllUserInfos() {
        return userSearchRepository.getAllUserInformation();
    }
}

