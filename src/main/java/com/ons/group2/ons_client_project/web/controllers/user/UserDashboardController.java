package com.ons.group2.ons_client_project.web.controllers.user;

import com.ons.group2.ons_client_project.model.HelpOffer;
import com.ons.group2.ons_client_project.model.HelpRequest;
import com.ons.group2.ons_client_project.model.UserInfo;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import com.ons.group2.ons_client_project.service.HelpRequestService;
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

    @Autowired
    private HelpOfferService helpOfferService;

    @Autowired
    private HelpRequestService helpRequestService;

    @GetMapping("user")
    public String userFinder(Model model) {


        // get all objects to be searched
        List<UserInfo> userInfos = userFinder.getAllUserInfos();
        List<HelpOffer> helpOffers = helpOfferService.getAllOffers();
        List<HelpRequest> helpRequests = helpRequestService.getAllRequests();

        model.addAttribute("userInfoKey", userInfos);
        model.addAttribute("content", "dashboard");
        model.addAttribute("helpOffers",helpOffers);
        model.addAttribute("helpRequests",helpRequests);


        System.out.println("user info = " + userInfos);

        return "user/index";
    }

//    @GetMapping("/user/profile")
//    public String userProfile(Model model) {
//        model.addAttribute("content", "profile");
//        return "user/index";
//    }


//    @GetMapping("/user")
//    public String userIndex(Model model) {
//        model.addAttribute("content", "dashboard");
//        return "user/index";
//    }
}
