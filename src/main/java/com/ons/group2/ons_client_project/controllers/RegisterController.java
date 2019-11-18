package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.NewUserDatabase;
import com.ons.group2.ons_client_project.service.UserService;
import org.hibernate.validator.internal.util.logging.Log;
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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);


    @RequestMapping(path = "/Register", method = RequestMethod.GET)
    public String register(Model model) {

        LOG.debug("Loading Registration form up");

        model.addAttribute("user", new User());
        return "t_register_form";

    }


//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ModelAndView registerUser(@ModelAttribute("user") @Valid User userKey,
//                                     BindingResult bindingResult,
//                                     ModelMap modelMap){
//
//        ModelAndView modelAndView = new ModelAndView();
//
//
//        modelAndView.addObject("user", new User());
//        modelAndView.setViewName("t_register_form");
//
//        return modelAndView;
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();

        //Check for validation
        if(bindingResult.hasErrors()){
            modelAndView.addObject("Message", "Please correct errors in form");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        //we will save the user, if no binding errors
        else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("Message", "user already exists");
        }else {
            //save the user registration form
            userService.saveUser(user);
            modelAndView.addObject("Message", "user created successfully");

        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("t_register_form");
        return modelAndView;
    }




//
//    //Servlet
//    @WebServlet("/register")
//    public class register extends HttpServlet {
//
//
//
//        private static final long serialVersionUID = 1l;
//
//        protected void doPost(HttpServletRequest request,
//                              HttpServletResponse response)throws ServletException, IOException{
//            try{
//                //Initialise the database
//                Connection con = NewUserDatabase.initializeDatabase();
//
//                //SQL queries
//                PreparedStatement st = con
//                        .prepareStatement("INSERT INTO user_table(user_name, email, password) VALUES(?,?,?)");
//
//                st.setString(1, request.getParameter("string"));
//                st.setString(2, request.getParameter("string"));
//                st.setString(3, request.getParameter("string"));
//
//                st.executeUpdate();
//
//                st.close();
//                con.close();
//
//                PrintWriter out = response.getWriter();
//                out.println("<html><body><b>Successfully Inserted"
//                        + "</b></body></html>");
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
