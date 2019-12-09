package com.ons.group2.ons_client_project.utils;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.security.UserPrincipal;
import org.springframework.security.core.Authentication;

public class UserUtils {

    public static User getUserFromAuth(Authentication auth) {
        return ((UserPrincipal) auth.getPrincipal()).getUser();
    }
}
