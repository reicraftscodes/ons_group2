package com.ons.group2.ons_client_project.service.userfinder;

import com.ons.group2.ons_client_project.model.UserInfo;

import java.util.List;

public interface UserSearchRepository {

    List<UserInfo> getUserInformation(Long userID);

    List<UserInfo> getAllUserInformation();

}
