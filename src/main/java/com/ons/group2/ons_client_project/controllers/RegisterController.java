package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);


    @RequestMapping(path = "/Register", method = RequestMethod.GET)
    public String register(Model model) {

        LOG.debug("Loading Registration form up");

        model.addAttribute("user", new User());
        return "t_register_form";

    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("user") @Valid User userKey,
                                     BindingResult bindingResult,
                                     ModelMap modelMap){

        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");

        return modelAndView;
    }


}
