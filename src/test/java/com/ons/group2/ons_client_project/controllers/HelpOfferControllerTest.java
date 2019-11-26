package com.ons.group2.ons_client_project.controllers;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.service.HelpOfferService;
import com.ons.group2.ons_client_project.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelpOfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private HelpOfferService helpOfferService;

    private final String testUserPass = "testHash";
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(
                1,
                "TestName",
                "test@email.com",
                testUserPass,
                "/images/test.png"
        );
        given(userService.findById(1)).willReturn(Optional.of(testUser));
    }





}
