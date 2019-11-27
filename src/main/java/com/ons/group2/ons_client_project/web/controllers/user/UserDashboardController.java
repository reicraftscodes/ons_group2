package com.ons.group2.ons_client_project.web.controllers.user;

import com.ons.group2.ons_client_project.model.UserInfo;
import com.ons.group2.ons_client_project.service.userfinder.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserDashboardController {

    @Autowired
    private UserFinder userFinder;

    //    insert your code here
    @GetMapping("user")
    public String userFinder(Model model) {

        List<UserInfo> userInfos = userFinder.getAllUserInfos();

        model.addAttribute("userInfoKey", userInfos);
        model.addAttribute("content", "dashboard");


        System.out.println("user info = " + userInfos);

        return "user/index";
    }

}
