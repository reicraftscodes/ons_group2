package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("t_register_form");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();

        //Check for validation
        if(bindingResult.hasErrors()){
            modelAndView.addObject("Message", "Please correct errors in form");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
//        we will save the user, if no binding errors
        else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("Message", "user already exists");
        }
        else {
            //save the user registration form
            userService.saveUser(user);
            modelAndView.addObject("Message", "user created successfully");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("t_register_form");
        return modelAndView;
    }


}
