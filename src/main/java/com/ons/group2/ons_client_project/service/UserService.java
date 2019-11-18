package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.User;

public interface UserService {

    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);

}
